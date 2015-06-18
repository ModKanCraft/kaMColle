package im.kaMColle;

import im.kaMColle.block.FleetSallyBoard;
import im.kaMColle.init.KamcolleBlocks;
import im.kaMColle.init.KamcolleItems;
import im.kaMColle.item.ItemSallyBoard;
import im.kaMColle.proxy.KamcolleClientProxy;
import im.kaMColle.proxy.KamcolleCommonProxy;
import im.kaMColle.tileEntity.SallyBoardTileEntity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="kamcolle", name="kaMColle", version=Kamcolle.VERSION)
public class Kamcolle {
	public static final String VERSION = "0.0.1";
	public static String ID="kamcolle";
	public static Logger LOGGER = LogManager.getLogger(ID);
	
	@SidedProxy(
            clientSide = "im.kaMColle.proxy.KamcolleClientProxy",
            serverSide = "im.kaMColle.proxy.KamcolleCommonProxy"
    )
    public static KamcolleCommonProxy proxy;
	
	@Instance("kamcolle")
	public static Kamcolle instance;
	public static KamcolleCreativeTab tab=new KamcolleCreativeTab("kanceaft");
	
	public static FleetSallyBoard blockSallyBoard=new FleetSallyBoard();
	
	
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
	     LOGGER.info("Starting kaMColle " + Kamcolle.VERSION);
	     //KamcolleClientProps.init();
	     KamcolleItems.init();
	     KamcolleBlocks.init();
	     proxy.preInit();
	     GameRegistry.registerTileEntity(SallyBoardTileEntity.class, "TileEntitySallyBoard");
	     //CORE_CONFIG.SaveConfig();
	}
	 
	@EventHandler
	public void load(FMLInitializationEvent event)
	{	
	     //EntityRegistry.addSpawn(EntityWoodKarakuriNingyG.class, 10, 4, 4, EnumCreatureType.monster);
		 proxy.init();
		 if(proxy instanceof KamcolleClientProxy){
			 ((KamcolleClientProxy) proxy).registerRenderThings();//calls the methods in our proxy, which will do things on client side
		     ((KamcolleClientProxy) proxy).registerSound();//
		 }
	     //GameRegistry.addRecipe(new ItemStack(sth, 1), new Object[]{"XYX",Character.valueOf('X'),s,Character.valueOf('Y'),th});
	}
	 
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		 proxy.postInit();
	}

}
