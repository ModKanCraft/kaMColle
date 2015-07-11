package im.kaMColle.proxy;

import im.kaMColle.handleEvent.KeyboardInputHandler;
import im.kaMColle.handleEvent.PlayerWithKandouInWaterHandler;
import im.kaMColle.network.packet.KansouSyncPacket;
import im.kaMColle.render.OBJBlockRenderer;
import im.kaMColle.render.RenderPlayerKansou;
import im.kaMColle.render.tileEntity.SallyBoardSpecialRenderer;
import im.kaMColle.tileEntity.SallyBoardTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KamcolleClientProxy extends KamcolleCommonProxy{

	public void preInit() {
		// TODO Auto-generated method stub
		super.preInit();
	}

	public void init() {
		// TODO Auto-generated method stub
		super.init();
		registerRenderThings();
		registerSound();
		FMLCommonHandler.instance().bus().register(new KeyboardInputHandler());
	}

	public void postInit() {
		// TODO Auto-generated method stub
		super.postInit();
	}

	public void registerRenderThings() {
		// TODO Auto-generated method stub
		RenderingRegistry.registerBlockHandler(new OBJBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(SallyBoardTileEntity.class, new SallyBoardSpecialRenderer());
		MinecraftForge.EVENT_BUS.register(new RenderPlayerKansou());
	}

	public void registerSound() {
		// TODO Auto-generated method stub
		
	}

	public EntityPlayer getPlayer(String name){
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
			return Minecraft.getMinecraft().theWorld.getPlayerEntityByName(name);
		else
			return super.getPlayer(name);
	}
	public void displayGUI(GuiScreen gui){
		Minecraft.getMinecraft().displayGuiScreen(gui);
	}
}
