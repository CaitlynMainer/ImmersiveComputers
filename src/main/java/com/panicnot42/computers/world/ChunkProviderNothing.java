package com.panicnot42.computers.world;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.panicnot42.computers.FutureComputers;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.MapGenBase;

public class ChunkProviderNothing implements IChunkProvider
{
    private Random rng;
    private World worldObj;
    private long seed;
    
    public ChunkProviderNothing(World world, long seed)
    {
    	this.seed = seed;
    	rng = new Random(seed);
    	this.worldObj = world;
    }
    
	@Override
	public boolean chunkExists(int x, int z)
	{
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		Chunk chunk = new Chunk(worldObj, x, z);
		Block block = FutureComputers.nothingBlock;
		Block blockCenter = Blocks.bedrock;
		Block blockNothing = Blocks.air;
        ExtendedBlockStorage[] ebs = chunk.getBlockStorageArray();
		for (int xl = 0; xl < 16; ++xl)
			for (int yl = 0; yl < 256; ++yl)
				for (int zl = 0; zl < 16; ++zl)
				{
					int ye = yl >> 4;
					if (ebs[ye] == null)
						ebs[ye] = new ExtendedBlockStorage(yl, !this.worldObj.provider.hasNoSky);
					ebs[ye].func_150818_a(xl, yl & 15, zl, ((Math.abs(x) % 32) > 15 || (Math.abs(z) % 32) > 15 || (yl == 0) || (yl == 255) || (xl == 0 && x % 16 == 0) || (zl == 0 && z % 16 == 0)) ? (yl == 0 && xl == 8 && zl == 8 && (Math.abs(x) % 32 == 8) && (Math.abs(z) % 32 == 8)) ? blockCenter : block : blockNothing);
				}

        chunk.generateSkylightMap();
        
        byte[] abyte = chunk.getBiomeArray();
        for (int l = 0; l < abyte.length; ++l)
        {
            abyte[l] = 0;
        }

        chunk.generateSkylightMap();
        
        return chunk;
	}

	@Override
	public Chunk loadChunk(int x, int z)
	{
		return this.provideChunk(x, z);
	}

	@Override
	public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
	{
	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
	{
		return false;
	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString()
	{
		return "Nothing";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
	{
		return null;
	}

	@Override
	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		return null;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public void recreateStructures(int p_82695_1_, int p_82695_2_)
	{
	}

	@Override
	public void saveExtraData()
	{
	}
}