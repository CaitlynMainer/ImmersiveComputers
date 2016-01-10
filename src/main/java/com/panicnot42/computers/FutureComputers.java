package com.panicnot42.computers;

import com.panicnot42.computers.block.BlockEnergyTurret;
import com.panicnot42.computers.block.BlockNothing;
import com.panicnot42.computers.block.BlockPocketDimensionBuilder;
import com.panicnot42.computers.entity.EntityEnergyBolt;
import com.panicnot42.computers.net.packet.PacketBoltFire;
import com.panicnot42.computers.tile.TileEntityEnergyTurret;
import com.panicnot42.computers.world.WorldProviderNothing;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DimensionManager;

@Mod(modid = FutureComputers.MODID, version = FutureComputers.VERSION,
	dependencies = "after:OpenComputers@[1.5.18,);")
public class FutureComputers
{
    @Instance(value = FutureComputers.MODID)
    public static FutureComputers instance;
    
    public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(FutureComputers.MODID);
    
    @SidedProxy(clientSide = "com.panicnot42.computers.client.ClientProxy", serverSide = "com.panicnot42.client.CommonProxy")
    public static CommonProxy proxy;
    
    public static final String MODID = "futurecomputers";
    public static final String VERSION = "1.0";

	public static final int dimensionId = 93;
    
    public static BlockEnergyTurret energyTurretBlock;
    public static BlockPocketDimensionBuilder dimensionBuilderBlock;
    public static BlockNothing nothingBlock;

	public static CreativeTabs creativeTab = new CreativeTabs("tabFutureComputers")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(FutureComputers.energyTurretBlock);
		}
	};
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        energyTurretBlock = new BlockEnergyTurret();
        dimensionBuilderBlock = new BlockPocketDimensionBuilder();
        nothingBlock = new BlockNothing();
        GameRegistry.registerBlock(energyTurretBlock, "energyturret");
        GameRegistry.registerBlock(dimensionBuilderBlock, "pocket");
        GameRegistry.registerBlock(nothingBlock, "nothing");
        GameRegistry.registerTileEntity(TileEntityEnergyTurret.class, "energyturret");
        EntityRegistry.registerModEntity(EntityEnergyBolt.class, "bolt", 0, instance, 64, 20, true);
        DimensionManager.registerProviderType(dimensionId, WorldProviderNothing.class, true);
        DimensionManager.registerDimension(dimensionId, dimensionId);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.registerRenders();
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event)
    {
    	network.registerMessage(PacketBoltFire.class, PacketBoltFire.class, 0, Side.CLIENT);
        
        ItemStack iron = new ItemStack(Items.iron_ingot);
        ItemStack chip = li.cil.oc.api.Items.get("chip2").createItemStack(1);
        ItemStack diamond = new ItemStack(Items.diamond);
        GameRegistry.addShapedRecipe(new ItemStack(energyTurretBlock, 1),
        		"ABA",
        		"BCB",
        		"ABA",
        		'A', iron,
        		'B', chip,
        		'C', diamond);
    }
}
