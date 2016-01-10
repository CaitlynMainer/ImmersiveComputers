package com.panicnot42.computers.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * EnergyBoltModel - panicnot42
 * Created using Tabula 5.1.0
 */
public class ModelEnergyBolt extends ModelBase {
    public ModelRenderer bolt;

    public ModelEnergyBolt() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bolt = new ModelRenderer(this, 23, 38);
        this.bolt.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.bolt.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.render(entity, f, f1, f2, f3, f4, f5, 0.0F, 0.0F);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float y, float p)
    {
    	setRotateAngle(bolt, p, y, 0.0F);
        this.bolt.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
