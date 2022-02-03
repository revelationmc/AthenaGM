package io.github.redwallhp.athenagm.maps;

import io.github.redwallhp.athenagm.AthenaGM;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the rotation of maps for an Arena
 */
public class Rotation {

    private AthenaGM plugin;
    private List<String> mapList;
    private List<GameMap> maps;
    private int mapIndex;


    /**
     * Constructor
     *
     * @param plugin  The AthenaGM instance
     * @param mapList String list of the maps in the configured map rotation
     */
    public Rotation(AthenaGM plugin, List<String> mapList) {
        this.plugin = plugin;
        this.mapList = mapList;
        this.mapIndex = 0;
        loadMaps();
    }


    /**
     * Create GameMap objects containing the metadata of every map configured for the arena
     *
     * @see GameMap
     */
    private void loadMaps() {
        this.maps = new ArrayList<GameMap>();
        for (String mapName : mapList) {
            plugin.getLogger().info("Attempting to load map: " + mapName);
            File dir = new File(plugin.getMapsDirectory(), mapName);
            if (!dir.exists()) {
                plugin.getLogger().warning(String.format("Map '%s' does not exist. Skipping.", mapName));
                continue;
            }
            try {
                GameMap map = new GameMap(dir);
                this.maps.add(map);
                plugin.getLogger().info("Loaded map: " + map.getName());
            } catch (Exception ex) {
                plugin.getLogger().warning(String.format("Error parsing maps info for '%s'. Skipping.", mapName));
                if (plugin.config.DEBUG) ex.printStackTrace();
            }
        }
    }


    /**
     * Returns the map list
     */
    public List<GameMap> getMaps() {
        return maps;
    }


    /**
     * Returns the map that is currently active
     */
    public GameMap getCurrentMap() {
        if (this.maps.isEmpty()) {
            return null;
        }
        try {
            return this.maps.get(mapIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            return this.maps.get(0);
        }
    }


    /**
     * Advance the rotation by one
     */
    public int advance() {
        mapIndex++;
        if (mapIndex > maps.size() - 1) mapIndex = 0;
        return mapIndex;
    }


    /**
     * Return the upcoming map in the rotation
     */
    public GameMap getNextMap() {
        if (this.maps.isEmpty()) {
            return null;
        }
        return maps.get(mapIndex);
    }


    /**
     * Advance the rotation to a specific named map
     *
     * @param mapName the map to change to
     * @return success
     */
    public boolean setNextMap(String mapName) {
        if (mapList.contains(mapName)) {
            mapIndex = mapList.indexOf(mapName);
            return true;
        }
        return false;
    }


    /**
     * Get a configured map by name
     *
     * @param name String representation of the map name
     */
    public GameMap getMapByName(String name) {
        for (GameMap map : maps) {
            if (map.getName().toLowerCase().equals(name.toLowerCase())) {
                return map;
            }
        }
        return null;
    }


    /**
     * Get a configured map by filename
     *
     * @param fileName String representation of the map's directory name
     */
    public GameMap getMapByFileName(String fileName) {
        for (GameMap map : maps) {
            if (map.getFileName().equalsIgnoreCase(fileName)) {
                return map;
            }
        }
        return null;
    }


}
