package im.kaMColle.proxy;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KamcolleCommonProxy {

	public void preInit(){}
	
 	public void init() {}

	public void postInit(){}
	@SideOnly(Side.SERVER)
	public EntityPlayer getPlayer(String name){
		EntityPlayer player;
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER){
			for(WorldServer w:MinecraftServer.getServer().worldServers){
				if((player=w.getPlayerEntityByName(name))!=null){
					return player;
				}
			}
		}
		return null;
	}
	public void displayGUI(GuiScreen gui){}
}