package im.kaMColle.proxy;

import im.kaMColle.render.RenderPlayerKansou;
import net.minecraftforge.common.MinecraftForge;

public class KamcolleClientProxy {
	public KamcolleClientProxy(){
		MinecraftForge.EVENT_BUS.register(new RenderPlayerKansou());
	}

	public void preInit() {
		// TODO Auto-generated method stub
		
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
