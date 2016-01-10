package com.panicnot42.computers.client;

import org.lwjgl.opengl.GL11;

import com.panicnot42.computers.tile.TileEntityEnergyTurret;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RendererItemEnergyTurret implements IItemRenderer
{
	private BlockEnergyTurretTESR render;
	private TileEntityEnergyTurret entity = new TileEntityEnergyTurret();
	
	public RendererItemEnergyTurret(BlockEnergyTurretTESR render)
	{
		this.render = render;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if(type == IItemRenderer.ItemRenderType.ENTITY)
			GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
		GL11.glTranslatef(0.0F, -0.2F, 0.0F);
		GL11.glScalef(0.85F, 0.85F, 0.85F);
		this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
	}
}