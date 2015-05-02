package im.kaMColle;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import im.kaMColle.proxy.KamcolleCommonProxy;


import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="kamcolle", name="kaMColle", version=Kamcolle.VERSION)
public class Kamcolle {
	public static final String VERSION = "0.0.1";
	public static String ID="kamcolle";
	public static Logger LOGGER = LogManager.getLogger(ID);
	
	@Instance("kamcolle")
	public static Kamcolle instance;
	
	@SidedProxy(
            clientSide = "im.kaMColle.proxy.KamcolleClientProxy",
            serverSide = "im.kaMColle.proxy.KamcolleCommonProxy"
    )
    public static KamcolleCommonProxy proxy;
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
	     LOGGER.info("Starting kaMColle " + Kamcolle.VERSION);
	     //KamcolleClientProps.init();
	     //KamcolleItems.init();
	     //KamcolleBlocks.init();
	     proxy.preInit();
	     //CORE_CONFIG.SaveConfig();
	}
	 
	@EventHandler
	public void load(FMLInitializationEvent event)
	{	
	     //EntityRegistry.addSpawn(EntityWoodKarakuriNingyG.class, 10, 4, 4, EnumCreatureType.monster);
		 proxy.init();
		 //clientProxy.registerRenderThings();//calls the methods in our proxy, which will do things on client side
	     //clientProxy.registerSound();
	     //GameRegistry.addRecipe(new ItemStack(sth, 1), new Object[]{"XYX",Character.valueOf('X'),s,Character.valueOf('Y'),th});
	}
	 
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		 proxy.postInit();
	}

}
