package im.kaMColle.proxy;

import im.kaMColle.ForTest;
import im.kaMColle.Kamcolle;
import im.kaMColle.handleEvent.PlayerWithKandouInWaterHandler;
import im.kaMColle.network.packet.KansouSyncPacket;
import im.kaMColle.tileEntity.SallyBoardTileEntity;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegInit;
import cn.annoreg.mc.gui.GuiHandlerBase;
import cn.annoreg.mc.gui.RegGuiHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class KamcolleCommonProxy{
	public void preInit(){}
	
 	public void init() {
	    Kamcolle.coreConfig.save();
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
}