package com.panicnot42.computers.client;

import org.lwjgl.opengl.GL11;

import com.panicnot42.computers.entity.EntityEnergyBolt;
import com.panicnot42.computers.model.ModelEnergyBolt;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityEnergyBolt extends Render
{
    private static final ResourceLocation textures = new ResourceLocation("futurecomputers:turret.png");
	private final ModelEnergyBolt model;

    public RenderEntityEnergyBolt()
	{
		model = new ModelEnergyBolt();
	}
    
	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_)
	{
		this.bindEntityTexture(entity);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.0F, (float)y + 1.0F + (4 * 0.0625F), (float)z + 0.0F);
		GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, ((EntityEnergyBolt)entity).getYaw(), -((EntityEnergyBolt)entity).getPitch());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return textures;
	}
}