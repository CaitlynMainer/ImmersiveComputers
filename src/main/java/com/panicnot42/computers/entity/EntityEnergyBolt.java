package com.panicnot42.computers.entity;

import java.util.List;

import com.panicnot42.computers.block.BlockEnergyTurret;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEnergyBolt extends Entity
{
	private int life    = 600;
	private float yaw   = 0.0F;
	private float pitch = 0.0F;
	private float damage = 0.0F;
	
	private static DamageSource energy;
	
	static
	{
		energy = new DamageSource("boltComputer");
		energy.setProjectile();
	}
	
	public EntityEnergyBolt(World world)
	{
		super(world);
		this.setSize(8 * 0.0625F, 8 * 0.0625F);
	}
	
	public void setHeading(float yaw, float pitch)
	{
		DataWatcher d = this.getDataWatcher();
		this.setVelocity((float)(-Math.sin(yaw) * Math.cos(pitch)), (float)-Math.sin(pitch), (float)(Math.cos(yaw) * Math.cos(pitch)));
		d.updateObject(19, this.yaw = yaw);
		d.updateObject(20, this.pitch = pitch);
	}
	
	public void setDamage(float damage)
	{
		this.getDataWatcher().updateObject(21, this.damage = damage);
	}

	@Override
	protected void entityInit()
	{
		DataWatcher d = this.getDataWatcher();
		d.addObject(19, yaw);
		d.addObject(20, pitch);
		d.addObject(21, damage);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag)
	{
		yaw = tag.getFloat("yaw");
		pitch = tag.getFloat("pitch");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag)
	{
		tag.setFloat("yaw", yaw);
		tag.setFloat("pitch", pitch);
	}
	
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	if (0 >= (life -= 1))
    		this.isDead = true;

		DataWatcher d = this.getDataWatcher();
    	yaw = d.getWatchableObjectFloat(19);
    	pitch = d.getWatchableObjectFloat(20);
    	damage = d.getWatchableObjectFloat(21);
        
        if (!worldObj.isAirBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY), (int)Math.floor(this.posZ)) &&
        		!(worldObj.getBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY), (int)Math.floor(this.posZ)) instanceof BlockEnergyTurret))
        	this.isDead = true;
    	
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);
        if (list.size() > 0)
        {
        	((Entity)list.get(0)).attackEntityFrom(energy, damage);
        	this.isDead = true;
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        
        this.setPosition(this.posX, this.posY, this.posZ);
        this.func_145775_I(); // hurr durr, mc does this so I does this
    }

	public float getYaw()
	{
		return yaw;
	}

	public float getPitch()
	{
		return pitch;
	}
}