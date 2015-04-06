package im.kaMColle;

import im.kaMColle.proxy.KamcolleClientProxy;
import im.kaMColle.proxy.KamcolleCommonProxy;

import java.io.File;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="kamcolle", name="kaMColle", version=Kamcolle.VERSION)
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class Kamcolle {
	public static final String VERSION = "0.0.1";
	public static Logger log = Logger.getLogger("kamcolle");
	
	@Instance("kamcolle")
	public static Kamcolle instance;
	
	@SidedProxy(
            clientSide = "im.kaMColle.proxy.KamcolleClientProxy",
            serverSide = "im.kaMColle.proxy.KamcolleCommonProxy"
    )
    public static KamcolleCommonProxy commonProxy;
	public static KamcolleClientProxy clientProxy;
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		 log.setParent(FMLLog.getLogger());
	     log.info("Starting Gimmickery " + Kamcolle.VERSION);
	     //KamcolleClientProps.init();
	     //KamcolleItems.init();
	     //KamcolleBlocks.init();
	     commonProxy.preInit();
	     clientProxy.preInit();
	     //CORE_CONFIG.SaveConfig();
	}
	 
	@EventHandler
	public void load(FMLInitializationEvent event)
	{	
	     //EntityRegistry.addSpawn(EntityWoodKarakuriNingyG.class, 10, 4, 4, EnumCreatureType.monster);
		 commonProxy.init();
		 clientProxy.init();
		 clientProxy.registerRenderThings();//calls the methods in our proxy, which will do things on client side
	     clientProxy.registerSound();
	     //GameRegistry.addRecipe(new ItemStack(sth, 1), new Object[]{"XYX",Character.valueOf('X'),s,Character.valueOf('Y'),th});
	}
	 
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		 commonProxy.postInit();
		 clientProxy.postInit();
	}

}
