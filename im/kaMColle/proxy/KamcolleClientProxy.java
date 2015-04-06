package im.kaMColle.proxy;

import java.util.logging.Level;

import im.kaMColle.Kamcolle;
import im.kaMColle.render.RenderPlayerKansou;
import net.minecraftforge.common.MinecraftForge;

public class KamcolleClientProxy {

	public void preInit() {
		// TODO Auto-generated method stub
		MinecraftForge.EVENT_BUS.register(new RenderPlayerKansou());
		Kamcolle.log.log(Level.FINE, "Event Regist Success");
	}

	public void init() {
		// TODO Auto-generated method stub
		
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
