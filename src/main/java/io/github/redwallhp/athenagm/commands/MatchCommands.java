package io.github.redwallhp.athenagm.commands;

import io.github.redwallhp.athenagm.AthenaGM;
import io.github.redwallhp.athenagm.arenas.Arena;
import io.github.redwallhp.athenagm.matches.PlayerScore;
import io.github.redwallhp.athenagm.matches.Team;
import io.github.redwallhp.athenagm.modules.chat.ChatModule;
import io.github.redwallhp.athenagm.utilities.PlayerUtil;
import io.github.redwallhp.athenagm.utilities.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;


public class MatchCommands implements CommandExecutor {


    private AthenaGM plugin;


    public MatchCommands(AthenaGM plugin) {
        this.plugin = plugin;
        plugin.getCommand("teams").setExecutor(this);
        plugin.getCommand("team").setExecutor(this);
        plugin.getCommand("autojoin").setExecutor(this);
        plugin.getCommand("spectate").setExecutor(this);
        plugin.getCommand("score").setExecutor(this);
        plugin.getCommand("players").setExecutor(this);
        plugin.getCommand("timeleft").setExecutor(this);
        plugin.getCommand("tmsg").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("teams")) {
            listTeams(sender);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("team")) {
            joinTeam(sender, args);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("autojoin")) {
            autoJoinTeam(sender);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("spectate")) {
            String[] arguments = {"spectator"};
            joinTeam(sender, arguments);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("score")) {
            printPlayerScore(sender);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("players")) {
            printPlayersList(sender);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("timeleft")) {
            timeLeft(sender, args);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tmsg")) {
            teamChat(sender, args);
            return true;
        }

        return false;

    }


    private void listTeams(CommandSender sender) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must join an arena to list teams.");
            return;
        }

        Player player = (Player) sender;
        Arena arena = plugin.getArenaHandler().getArenaForPlayer(player);

        if (arena == null) {
            sender.sendMessage(ChatColor.RED + "You must join an arena to list teams.");
            return;
        }

        List<String> teamStrings = new ArrayList<String>();
        for (Team team : arena.getMatch().getTeams().values()) {
            teamStrings.add(String.format("%s%s (%d/%d)%s", team.getChatColor(), team.getId(), team.getPlayers().size(), team.getSize(), ChatColor.RESET));
        }

        String list = StringUtil.joinList(", ", teamStrings);
        sender.sendMessage(String.format("%sTeams: %s", ChatColor.DARK_AQUA, list));

    }


    private void joinTeam(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console can't join a team.");
            return;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /team <team id>");
            return;
        }

        Player player = (Player) sender;
        Arena arena = plugin.getArenaHandler().getArenaForPlayer(player);

        if (arena == null) {
            sender.sendMessage(ChatColor.RED + "You must join an arena first.");
            return;
        }

        for (Team team : arena.getMatch().getTeams().values()) {
            if (team.getId().equalsIgnoreCase(args[0])) {
                team.add(player, false);
                return;
            }
        }

        sender.sendMessage(ChatColor.RED + "Invalid team id.");

    }


    private void autoJoinTeam(CommandSender sender) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console can't join a team.");
            return;
        }

        Player player = (Player) sender;
        Arena arena = plugin.getArenaHandler().getArenaForPlayer(player);

        if (arena == null) {
            sender.sendMessage(ChatColor.RED + "You must join an arena first.");
            return;
        }

        Team lowest = null;
        for (Team t : arena.getMatch().getTeams().values()) {
            if (t.isSpectator()) continue;
            if (t.getPlayers().size() >= t.getSize()) continue;
            if (lowest == null || t.getPlayers().size() < lowest.getPlayers().size()) {
                lowest = t;
            }
        }

        if (lowest != null) {
            lowest.add(player, false);
        } else {
            sender.sendMessage(ChatColor.RED + "Unable to join team.");
        }

    }


    private void printPlayerScore(CommandSender sender) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console can't have a score.");
            return;
        }

        Player player = (Player) sender;
        Arena arena = plugin.getArenaHandler().getArenaForPlayer(player);
        if (arena == null) {
            sender.sendMessage(ChatColor.RED + "You must join an arena first.");
            return;
        }
        Team team = PlayerUtil.getTeamForPlayer(arena.getMatch(), player);
        if (team == null || team.isSpectator()) {
            sender.sendMessage(ChatColor.RED + "You must join a team to have a score.");
            return;
        }
        PlayerScore playerScore = team.getPlayerScore(player);

        LinkedHashMap<String, Integer> values = new LinkedHashMap<String, Integer>();
        values.put("Points", playerScore.getPoints());
        values.put("Kills", playerScore.getKills());
        values.put("Deaths", playerScore.getDeaths());
        try {
            values.put("KDR", (playerScore.getKills() / playerScore.getDeaths()));
        } catch (ArithmeticException ex) {
            if (playerScore.getKills() > 0) {
                values.put("KDR", playerScore.getKills());
            } else {
                values.put("KDR", 0);
            }
        }

        StringBuilder sb = new StringBuilder(ChatColor.DARK_AQUA + "Personal score: ");
        for (Map.Entry<String, Integer> pair : values.entrySet()) {
            sb.append(String.format("%s%s: %s%d", ChatColor.AQUA, pair.getKey(), ChatColor.GREEN, pair.getValue()));
            sb.append(" ");
        }
        sender.sendMessage(sb.toString());

    }


    private void printPlayersList(CommandSender sender) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console can't join a team.");
            return;
        }

        Player player = (Player) sender;
        Arena arena = plugin.getArenaHandler().getArenaForPlayer(player);

        if (arena == null) {
            sender.sendMessage(ChatColor.RED + "You must join an arena first.");
            return;
        }

        for (Team team : arena.getMatch().getTeams().values()) {

            if (team.getPlayers().size() < 1) continue;

            TreeMap<Integer, PlayerScore> ranking = new TreeMap<Integer, PlayerScore>();
            for (Player p : team.getPlayers()) {
                PlayerScore ps = team.getPlayerScore(p);
                ranking.put(ps.getOverallScore(), ps);
            }

            StringBuilder sb = new StringBuilder(team.getColoredName() + ChatColor.RESET + ": ");
            for (PlayerScore ps : ranking.values()) {
                sb.append(String.format("%s %s(%d)%s", ps.getPlayer().getName(), ChatColor.GRAY, ps.getOverallScore(), ChatColor.RESET));
                if (ranking.lastEntry().getValue() != ps) {
                    sb.append(", ");
                }
            }
            sender.sendMessage(sb.toString());

        }

    }


    private void timeLeft(CommandSender sender, String[] args) {

        Arena arena = null;

        if (!(sender instanceof Player) && args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Specify an arena name to see the time left in the match. (/timeleft <arena>)");
            return;
        }

        if (args.length == 1) {
            for (Arena a : plugin.getArenaHandler().getArenas()) {
                if (a.getId().equalsIgnoreCase(args[0])) {
                    arena = a;
                    break;
                }
            }
        } else {
            Player player = (Player) sender;
            arena = plugin.getArenaHandler().getArenaForPlayer(player);
        }

        if (arena != null) {
            String secString = "00";
            String minString = "00";
            if (arena.getMatch().getTimer() != null) {
                long secondsLeft = arena.getMatch().getTimer().timeLeftInSeconds();
                long sec = secondsLeft % 60;
                long min = (secondsLeft / 60) % 60;
                secString = String.format("%02d", sec);
                minString = String.format("%02d", min);
            }
            sender.sendMessage(String.format("%s%s:%s", ChatColor.DARK_AQUA, minString, secString));
        } else {
            sender.sendMessage(ChatColor.RED + "You must join an arena first, or use /timeleft <arena>");
        }

    }


    private void teamChat(CommandSender sender, String[] args) {

        if (!(sender instanceof Player) || args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Send a message to your team: /t <msg>");
            return;
        } else {
            ChatModule cm = plugin.getModule(ChatModule.class);
            boolean success = cm.sendTeamMessage((Player) sender, StringUtil.joinArray(" ", args));
            if (!success) {
                sender.sendMessage(ChatColor.RED + "You must be on a team to do that.");
            }
        }

    }


}
