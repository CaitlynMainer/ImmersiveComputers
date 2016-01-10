package com.panicnot42.computers.world;

import java.util.HashMap;

import com.panicnot42.computers.FutureComputers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TeleporterNothing extends Teleporter
{
	private final static TeleporterNothing teleporter = new TeleporterNothing(MinecraftServer.getServer().worldServerForDimension(FutureComputers.dimensionId));
    private final WorldServer worldServerInstance;
    private static HashMap<Integer, Boolean> generatedLocations = new HashMap<Integer, Boolean>();
    
    public static void SendToNothing(EntityPlayerMP player, Integer locationNum)
    {
    	player.setPosition(16 * locationNum, 10.0D, 0.0D);
    	if (!(generatedLocations.containsKey(locationNum) && generatedLocations.get(locationNum)))
    	{
    		teleporter.worldServerInstance.setBlock(0, 0, 0, Blocks.bookshelf);
    	}
    	MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, FutureComputers.dimensionId, teleporter);
    }
    
	public TeleporterNothing(WorldServer server)
	{
		super(server);
		this.worldServerInstance = server;
	}
	
	@Override
	public void placeInPortal(Entity entity, double x, double y, double z, float facing)
	{
		placeInExistingPortal(entity, x, y, z, facing);
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float facing)
	{
		return true;
	}

	@Override
	public boolean makePortal(Entity entity)
	{
		throw new NotImplementedException();
	}

	@Override
	public void removeStalePortalLocations(long time)
	{
	}
}