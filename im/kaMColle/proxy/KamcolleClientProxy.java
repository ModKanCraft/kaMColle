package im.kaMColle.proxy;

import java.util.logging.Level;

import im.kaMColle.Kamcolle;
import im.kaMColle.render.RenderPlayerKansou;
import net.minecraftforge.common.MinecraftForge;

public class KamcolleClientProxy extends KamcolleCommonProxy{

	public void preInit() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub
		Kamcolle.log.info("Try Event Regist");
	    MinecraftForge.EVENT_BUS.register(new RenderPlayerKansou());
	    Kamcolle.log.info("Event Regist Finished");
	}

	public void postInit() {
		// TODO Auto-generated method stub
		
	}

	public void registerRenderThings() {
		// TODO Auto-generated method stub
		
	}

	public void registerSound() {
		// TODO Auto-generated method stub
		
	}
}
