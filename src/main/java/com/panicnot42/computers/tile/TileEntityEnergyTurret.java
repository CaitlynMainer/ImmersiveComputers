package com.panicnot42.computers.tile;

import com.panicnot42.computers.entity.EntityEnergyBolt;

import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Connector;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.TileEntityEnvironment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityEnergyTurret extends TileEntityEnvironment
{
	public float yaw = 0.0F;
	public float pitch = 0.0F;
	
	public float setpointYaw = 0.0F;
	public float setpointPitch = 0.0F;
	public int tickCool = 0;
	
	public boolean onPoint = true;
	
	private final float movePerTick = 0.005F;
    
	public TileEntityEnergyTurret()
	{
		node = Network.newNode(this, Visibility.Network).withComponent("energyturret", Visibility.Network).withConnector().create();
	}
	
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        write(syncData);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        NBTTagCompound tag = pkt.func_148857_g();
        read(tag);
    }
    
    @Override
    public void updateEntity()
    {
    	super.updateEntity();
    	--tickCool;
    	float dy = setpointYaw - yaw;
    	float dp = setpointPitch - pitch;
    	float my = Math.min(movePerTick, Math.abs(dy));
    	float mp = Math.min(movePerTick, Math.abs(dp));
    	yaw += my * Math.signum(dy);
    	pitch += mp * Math.signum(dp);
    	onPoint = (Math.abs(dy) < movePerTick) && (Math.abs(dp) < movePerTick); 
    }
    
    @Callback(doc = "function():number -- Current real yaw")
    public Object[] getYaw(Context context, Arguments args)
    {
    	return new Object[] { yaw };
    }
    
    @Callback(doc = "function():number -- Current real pitch")
    public Object[] getPitch(Context context, Arguments args)
    {
    	return new Object[] { pitch };
    }
    
    @Callback(doc = "function():boolean -- Returns whether the gun has reached the set position")
    public Object[] isOnTarget(Context context, Arguments args)
    {
    	return new Object[] { onPoint };
    }

    @Callback(doc = "function():boolean -- Returns whether the gun is cool enough to fire again")
    public Object[] isReady(Context context, Arguments args)
    {
    	return new Object[] { !(tickCool > 0) };
    }

    @Callback(doc = "function(yaw:number, pitch:number) -- Changes the gun's setpoint (ranges (0.0..1.0))")
    public Object[] moveTo(Context context, Arguments args)
    {
    	setpointYaw = MathHelper.clamp_float((float)args.checkDouble(0), 0.0F, 1.0F);
    	setpointPitch = MathHelper.clamp_float((float)args.checkDouble(1), 0.0F, 1.0F);;
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	markDirty();
        return new Object[] {};
    }

    @Callback(doc = "function(damage:number):table -- Fires the gun.  More damage means longer cooldown and more energy draw.  Returns 0 for success and -1 with a message for failure")
    public Object[] fire(Context context, Arguments args)
    {
        float p = getRealPitch();
        float a = getRealYaw() + (float)Math.PI;
        float damage = MathHelper.clamp_float((float)args.checkDouble(0), 0.0F, 50.0F);
    	EntityEnergyBolt bolt = new EntityEnergyBolt(worldObj);
    	bolt.setHeading(a, p);
    	bolt.setDamage(damage);
        bolt.setPosition(xCoord + 0.5F, yCoord + 0.85F, zCoord + 0.5F);
        
        if (!((Connector)node).tryChangeBuffer(-1))
        	return new Object[] { -1, "not enough energy" };
        if (tickCool > 0)
        	return new Object[] { -1, "gun hasn't cooled" };
        
        tickCool = 200;
        
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	markDirty();
        
        this.worldObj.spawnEntityInWorld(bolt);
        return new Object[] { 0 };
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
    	super.readFromNBT(tag);
    	read(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
    	super.writeToNBT(tag);
    	write(tag);
    }

    private void write(NBTTagCompound tag)
    {
    	tag.setFloat("yaw", yaw);
    	tag.setFloat("pitch", pitch);
    	tag.setFloat("syaw", setpointYaw);
    	tag.setFloat("spitch", setpointPitch);
    	tag.setInteger("cool", tickCool);
    }
    
    private void read(NBTTagCompound tag)
    {
        yaw = tag.getFloat("yaw");
        pitch = tag.getFloat("pitch");
        setpointYaw = tag.getFloat("syaw");
        setpointPitch = tag.getFloat("spitch");
        tickCool = tag.getInteger("cool");
    }
    
    public float getRealYaw()
    {
    	return ((float)Math.PI) * 2.0F * yaw;
    }
    
    public float getRealPitch()
    {
    	return ((float)Math.PI) * 0.5F * -pitch;
    }
}
