package im.kaMColle.proxy;

import java.util.logging.Level;

import cpw.mods.fml.client.registry.RenderingRegistry;
import im.kaMColle.Kamcolle;
import im.kaMColle.item.ItemSallyBoard;
import im.kaMColle.render.OBJBlockRenderer;
import im.kaMColle.render.OBJItemRenderer;
import im.kaMColle.render.RenderPlayerKansou;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class KamcolleClientProxy extends KamcolleCommonProxy{

	public void preInit() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub
		Kamcolle.LOGGER.info("Try Event Regist");
	    MinecraftForge.EVENT_BUS.register(new RenderPlayerKansou());
	    Kamcolle.LOGGER.info("Event Regist Finished");
	}

	public void postInit() {
		// TODO Auto-generated method stub
		
	}

	public void registerRenderThings() {
		// TODO Auto-generated method stub
		RenderingRegistry.registerBlockHandler(new OBJBlockRenderer());
	}

	public void registerSound() {
		// TODO Auto-generated method stub
		
	}
	void test(){
		
	}
}
