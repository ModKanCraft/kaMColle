package im.kaMColle;

import im.kaMColle.block.FleetSallyBoard;
import im.kaMColle.config.KamcolleConfig;
import im.kaMColle.handleEvent.PlayerWithKandouInWaterHandler;
import im.kaMColle.init.KamcolleBlocks;
import im.kaMColle.init.KamcolleItems;
import im.kaMColle.network.MessageHandler;
import im.kaMColle.network.packet.KansouSync;
import im.kaMColle.proxy.KamcolleClientProps;
import im.kaMColle.proxy.KamcolleCommonProxy;
import im.kaMColle.tileEntity.SallyBoardTileEntity;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

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
	public static final String VERSION = "0.0.2";
	public static final String ID="kamcolle";
	public static final Logger LOGGER = LogManager.getLogger(ID);	
	public static KamcolleConfig coreConfig;
	//public static int message_Index=0;
	
	@SidedProxy(
            clientSide = "im.kaMColle.proxy.KamcolleClientProxy",
            serverSide = "im.kaMColle.proxy.KamcolleCommonProxy"
    )
    public static KamcolleCommonProxy proxy;
	
	@Instance("kamcolle")
	public static Kamcolle instance;
	public static KamcolleCreativeTab TAB=new KamcolleCreativeTab("kanceaft");
	public static FleetSallyBoard blockSallyBoard=new FleetSallyBoard();
	
	

	
	public static void LogInfo(Object obj){
		if(obj==null)
			LOGGER.info("null");
		else
			LOGGER.info(obj);
	}
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
	    LOGGER.info("Starting kaMColle " + Kamcolle.VERSION);
	    coreConfig=new KamcolleConfig(new File("config/Kamcolle.config"));
	    KamcolleClientProps.init();
	    KamcolleItems.init();
	    KamcolleBlocks.init();
	    proxy.preInit();
	    GameRegistry.registerTileEntity(SallyBoardTileEntity.class, "TileEntitySallyBoard");
	    coreConfig.save();
	}
	 
	@EventHandler
	public void load(FMLInitializationEvent event)
	{	
	    //EntityRegistry.addSpawn(EntityWoodKarakuriNingyG.class, 10, 4, 4, EnumCreatureType.monster);
		proxy.init();
		MessageHandler.init();
		MinecraftForge.EVENT_BUS.register(new PlayerWithKandouInWaterHandler());
		//MinecraftForge.EVENT_BUS.register(new KamcolleKansouChange());
	    //GameRegistry.addRecipe(new ItemStack(sth, 1), new Object[]{"XYX",Character.valueOf('X'),s,Character.valueOf('Y'),th});
	}
	 
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}

}
