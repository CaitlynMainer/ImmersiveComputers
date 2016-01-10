package com.panicnot42.computers.block;

import com.panicnot42.computers.FutureComputers;
import com.panicnot42.computers.tile.TileEntityEnergyTurret;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockEnergyTurret extends Block implements ITileEntityProvider
{
    public BlockEnergyTurret()
    {
        super(Material.anvil);
        this.setHardness(6);
        this.setStepSound(soundTypeMetal);
        this.setBlockName("energyTurret");
        this.setCreativeTab(FutureComputers.instance.creativeTab);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityEnergyTurret();
    }
    
    @Override
    public int getRenderType()
    {
    	return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
    	return false;
    }
    
    public boolean renderAsNormalBlock()
    {
    	return false;
    }
    
    @Override
    public IIcon getIcon(int side, int meta)
    {
    	return Blocks.iron_block.getIcon(side, meta);
    }
}
