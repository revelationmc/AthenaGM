package io.github.redwallhp.athenagm.utilities;


import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.selector.CuboidRegionSelector;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Optional;

/**
 * WorldEdit functions, isolated in a container class so AthenaGM doesn't depend on WorldEdit.
 * Always use AthenaGM's getWE() method to check if WorldEdit is present before calling one of these
 * static methods.
 */
public class WorldEditUtil {
    /**
     * Set the player's WorldEdit selection
     *
     * @param player The player
     * @param world  The world the selection is in
     * @param min    The first corner of the selection
     * @param max    The second corner of the selection
     */
    public static void setPlayerSelection(Player player, World world, Vector min, Vector max) {
        final Optional<WorldEditPlugin> worldEdit = getWorldEdit();
        if (worldEdit.isPresent()) {
            final LocalSession session = getWorldEdit().get().getSession(player);
            final CuboidRegionSelector selector = new CuboidRegionSelector(session.getSelectionWorld(), BlockVector3.at(min.getX(), min.getY(), min.getZ()), BlockVector3.at(max.getX(), max.getY(), max.getZ()));
            session.setRegionSelector(session.getSelectionWorld(), selector);
        }
    }

    /**
     * Get a reference to WE. For use internal to this class only, otherwise it breaks the soft dependency.
     * Always check if WorldEdit is present using the method in the main plugin class.
     */
    private static Optional<WorldEditPlugin> getWorldEdit() {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("WorldEdit")) {
            return Optional.of(WorldEditPlugin.getPlugin(WorldEditPlugin.class));
        }
        return Optional.empty();
    }
}
