package io.github.redwallhp.athenagm.utilities;

import org.bukkit.Color;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class ItemUtil {


    /**
     * A list of Materials that do something when right-clicked.
     * e.g. for blacklisting in PlayerInteractEvent in spectator mode.
     */
    public static Set<Material> getInteractiveBlocks() {
        Set<Material> interactive = new HashSet<>();
        interactive.add(Material.ACACIA_DOOR);
        interactive.add(Material.ACACIA_FENCE_GATE);
        interactive.add(Material.ANVIL);
        interactive.add(Material.ARMOR_STAND);
        interactive.add(Material.BEACON);
        interactive.add(Material.RED_BED);
        interactive.add(Material.BIRCH_DOOR);
        interactive.add(Material.BIRCH_FENCE_GATE);
        interactive.add(Material.BREWING_STAND);
        interactive.add(Material.CAKE);
        interactive.add(Material.CHEST);
        interactive.add(Material.DARK_OAK_DOOR);
        interactive.add(Material.DARK_OAK_FENCE_GATE);
        interactive.add(Material.DAYLIGHT_DETECTOR);
        interactive.add(Material.REPEATER);
        interactive.add(Material.DISPENSER);
        interactive.add(Material.DROPPER);
        interactive.add(Material.ENCHANTING_TABLE);
        interactive.add(Material.ENDER_CHEST);
        interactive.add(Material.OAK_FENCE_GATE);
        interactive.add(Material.FURNACE);
        interactive.add(Material.HOPPER);
        interactive.add(Material.ITEM_FRAME);
        interactive.add(Material.IRON_TRAPDOOR);
        interactive.add(Material.JUKEBOX);
        interactive.add(Material.JUNGLE_DOOR);
        interactive.add(Material.JUNGLE_FENCE_GATE);
        interactive.add(Material.LEVER);
        interactive.add(Material.NOTE_BLOCK);
        interactive.add(Material.COMPARATOR);
        interactive.add(Material.SPRUCE_DOOR);
        interactive.add(Material.SPRUCE_FENCE_GATE);
        interactive.add(Material.STONE_BUTTON);
        interactive.add(Material.OAK_TRAPDOOR);
        interactive.add(Material.TRAPPED_CHEST);
        interactive.add(Material.OAK_BUTTON);
        interactive.add(Material.OAK_DOOR);
        interactive.add(Material.CRAFTING_TABLE);
        return interactive;
    }


    /**
     * Returns limited Color objects (e.g. for team-colored armor) based
     * on a string representation of a ChatColor returned from the Team object.
     * @param color String representation of a ChatColor object (as returned from Team object)
     * @return Color object
     */
    public static Color getColorFormString(String color) {
        if (color.equalsIgnoreCase("red")) {
            return Color.RED;
        }
        else if (color.equalsIgnoreCase("blue")) {
            return Color.BLUE;
        }
        else if (color.equalsIgnoreCase("green")) {
            return Color.GREEN;
        }
        else if (color.equalsIgnoreCase("aqua")) {
            return Color.AQUA;
        }
        else if (color.equalsIgnoreCase("dark_aqua")) {
            return Color.fromRGB(0, 170, 170);
        }
        else if (color.equalsIgnoreCase("dark_blue")) {
            return Color.fromRGB(0, 0, 170);
        }
        else if (color.equalsIgnoreCase("dark_gray")) {
            return Color.fromRGB(85, 85, 85);
        }
        else if (color.equalsIgnoreCase("dark_green")) {
            return Color.fromRGB(0, 170, 0);
        }
        else if (color.equalsIgnoreCase("dark_purple")) {
            return Color.PURPLE;
        }
        else if (color.equalsIgnoreCase("dark_red")) {
            return Color.fromRGB(170, 0, 0);
        }
        else if (color.equalsIgnoreCase("light_purple")) {
            return Color.fromRGB(255, 85, 255);
        }
        else if (color.equalsIgnoreCase("gold")) {
            return Color.ORANGE;
        }
        else if (color.equalsIgnoreCase("black")) {
            return Color.BLACK;
        }
        else if (color.equalsIgnoreCase("white")) {
            return Color.WHITE;
        }
        else if (color.equalsIgnoreCase("yellow")) {
            return Color.YELLOW;
        }
        else {
            return Color.GRAY;
        }
    }


    /**
     * Returns a color byte for setData(), because the Bukkit API still doesn't handle
     * stained glass colors well...
     * @param color String representation of a ChatColor object (as returned from Team object)
     * @return Stained glass color representation
     */
    public static byte getDyeColorByte(String color) {
        if (color.equalsIgnoreCase("red")) {
            return 14;
        }
        else if (color.equalsIgnoreCase("blue")) {
            return 11;
        }
        else if (color.equalsIgnoreCase("green")) {
            return 5;
        }
        else if (color.equalsIgnoreCase("aqua")) {
            return 9;
        }
        else if (color.equalsIgnoreCase("dark_aqua")) {
            return 9;
        }
        else if (color.equalsIgnoreCase("dark_blue")) {
            return 11;
        }
        else if (color.equalsIgnoreCase("dark_gray")) {
            return 7;
        }
        else if (color.equalsIgnoreCase("dark_green")) {
            return 13;
        }
        else if (color.equalsIgnoreCase("dark_purple")) {
            return 10;
        }
        else if (color.equalsIgnoreCase("dark_red")) {
            return 14;
        }
        else if (color.equalsIgnoreCase("light_purple")) {
            return 2;
        }
        else if (color.equalsIgnoreCase("gold")) {
            return 1;
        }
        else if (color.equalsIgnoreCase("black")) {
            return 15;
        }
        else if (color.equalsIgnoreCase("white")) {
            return 0;
        }
        else if (color.equalsIgnoreCase("yellow")) {
            return 4;
        }
        else {
            return 8;
        }
    }


}
