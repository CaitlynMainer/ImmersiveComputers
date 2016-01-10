package com.panicnot42.computers.world;

import java.util.Arrays;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

class WorldChunkManagerNothing extends WorldChunkManager
{
    private BiomeGenBase biomeGenerator;
    private float rainfall;
    
    public WorldChunkManagerNothing(BiomeGenBase gen, float rainfall)
    {
        this.biomeGenerator = gen;
        this.rainfall = rainfall;
    }

    public BiomeGenBase getBiomeGenAt(int x, int z)
    {
        return this.biomeGenerator;
    }
    
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] gen, int p_76937_2_, int p_76937_3_, int width, int height)
    {
        if (gen == null || gen.length < width * height)
        {
            gen = new BiomeGenBase[width * height];
        }

        Arrays.fill(gen, 0, width * height, this.biomeGenerator);
        return gen;
    }


    public float[] getRainfall(float[] rainfall, int p_76936_2_, int p_76936_3_, int width, int height)
    {
        if (rainfall == null || rainfall.length < width * height)
        {
            rainfall = new float[width * height];
        }

        Arrays.fill(rainfall, 0, width * height, this.rainfall);
        return rainfall;
    }
}