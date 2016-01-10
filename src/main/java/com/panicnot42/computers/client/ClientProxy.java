package com.panicnot42.computers.client;

import com.panicnot42.computers.CommonProxy;
import com.panicnot42.computers.FutureComputers;
import com.panicnot42.computers.entity.EntityEnergyBolt;
import com.panicnot42.computers.tile.TileEntityEnergyTurret;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	public void registerRenders()
	{
		BlockEnergyTurretTESR render = new BlockEnergyTurretTESR();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyTurret.class, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FutureComputers.energyTurretBlock), new RendererItemEnergyTurret(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnergyBolt.class, new RenderEntityEnergyBolt());
	}
}