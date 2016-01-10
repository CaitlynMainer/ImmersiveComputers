package com.panicnot42.computers.world;

import com.panicnot42.computers.FutureComputers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderNothing extends WorldProvider
{
	private float[] skyColors = new float[4];
	
	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, this.dimensionId);
		this.dimensionId = FutureComputers.dimensionId;
		this.hasNoSky = true;
	}
	
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderNothing(this.worldObj, this.worldObj.getSeed());
	}
	
	@Override
	public String getDimensionName()
	{
		return "Nothing";
	}
	
	@Override
	public int getAverageGroundLevel()
	{
		return 0;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float x, float z)
    {
        return Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
    }
	
	@Override
    public boolean isSurfaceWorld()
    {
        return false;
    }
	
    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }

    public float calculateCelestialAngle(long time, float p_76563_3_)
    {
        return 0.5F;
    }

    public boolean canRespawnHere()
    {
        return false;
    }
}