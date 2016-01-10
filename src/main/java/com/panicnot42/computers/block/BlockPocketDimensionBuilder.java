package com.panicnot42.computers.block;

import com.panicnot42.computers.FutureComputers;
import com.panicnot42.computers.world.TeleporterNothing;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class BlockPocketDimensionBuilder extends Block
{
	public BlockPocketDimensionBuilder()
	{
		super(Material.anvil);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int thing, float help, float dont, float know)
	{
		if (!world.isRemote)
			TeleporterNothing.SendToNothing((EntityPlayerMP) player, 0);
		return true;
	}
}