package com.panicnot42.computers.client;

import org.lwjgl.opengl.GL11;

import com.panicnot42.computers.model.ModelEnergyTurret;
import com.panicnot42.computers.tile.TileEntityEnergyTurret;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class BlockEnergyTurretTESR extends TileEntitySpecialRenderer
{
	private final ModelEnergyTurret model;
	ResourceLocation textures;
	
	public BlockEnergyTurretTESR()
	{
		model = new ModelEnergyTurret();
		textures = (new ResourceLocation("futurecomputers:turret.png"));
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, ((TileEntityEnergyTurret)te).getRealYaw(), ((TileEntityEnergyTurret)te).getRealPitch());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}
}