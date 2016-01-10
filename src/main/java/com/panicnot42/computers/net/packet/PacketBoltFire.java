package com.panicnot42.computers.net.packet;

import com.panicnot42.computers.entity.EntityEnergyBolt;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketBoltFire implements IMessage, IMessageHandler<PacketBoltFire, IMessage>
{
	float yaw;
	float pitch;
	int entityId;
	
	public PacketBoltFire()
	{
	}
	
	public PacketBoltFire(float yaw, float pitch)
	{
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	@Override
	public IMessage onMessage(PacketBoltFire message, MessageContext ctx)
	{
		((EntityEnergyBolt)(Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(entityId))).setHeading(yaw, pitch); // holy derp will crash
		
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		entityId = buf.readInt();
		yaw = buf.readFloat();
		pitch = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(entityId);
		buf.writeFloat(yaw);
		buf.writeFloat(pitch);
	}
}