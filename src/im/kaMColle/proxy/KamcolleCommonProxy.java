package im.kaMColle.proxy;

import im.kaMColle.ForTest;
import im.kaMColle.handleEvent.PlayerWithKandouInWaterHandler;
import im.kaMColle.network.MessageHandler;
import im.kaMColle.network.packet.KansouSyncPacket;
import im.kaMColle.tileEntity.SallyBoardTileEntity;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;

public class KamcolleCommonProxy {

	public void preInit(){
	    GameRegistry.registerTileEntity(SallyBoardTileEntity.class, "TileEntitySallyBoard");
	    }
	
 	public void init() {
		MessageHandler.init();
		MinecraftForge.EVENT_BUS.register(new PlayerWithKandouInWaterHandler());
		MinecraftForge.EVENT_BUS.register(new KansouSyncPacket());
		MinecraftForge.EVENT_BUS.register(new ForTest());
 	}

	public void postInit(){}
	
	public EntityPlayer getPlayer(String name){
		EntityPlayer player;
		for(WorldServer w:MinecraftServer.getServer().worldServers){
			if((player=w.getPlayerEntityByName(name))!=null){
				return player;
			}
		}
		return null;
	}
	public void displayGUI(GuiScreen gui){}
}