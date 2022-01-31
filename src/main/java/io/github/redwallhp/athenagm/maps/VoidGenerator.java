package io.github.redwallhp.athenagm.maps;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

/**
 * Void world generator used to prevent the creation of new chunks around loaded map worlds
 */
public class VoidGenerator extends ChunkGenerator {
    @Override
    public void generateSurface(WorldInfo worldInfo, Random random, int x, int z, ChunkData chunkData) {
    }

    @Override
    public void generateBedrock(WorldInfo worldInfo, Random random, int x, int z, ChunkData chunkData) {
    }

    @Override
    public void generateCaves(WorldInfo worldInfo, Random random, int x, int z, ChunkData chunkData) {
    }

    /*@Override
    public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        return new byte[16][];
    }*/

}
