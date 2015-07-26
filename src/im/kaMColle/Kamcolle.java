package im.kaMColle;

import im.kaMColle.block.FleetSallyBoard;
import im.kaMColle.config.KamcolleConfig;
import im.kaMColle.item.KamcolleFleetCard;
import im.kaMColle.proxy.KamcolleCommonProxy;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.annoreg.core.Registrant;
import cn.annoreg.core.RegistrationManager;
import cn.annoreg.core.RegistrationMod;
import cn.annoreg.mc.RegBlock;
import cn.annoreg.mc.RegInit;
import cn.annoreg.mc.RegItem;
import cn.annoreg.mc.RegMessageHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
@Mod(modid="kamcolle", name="kaMColle", version=Kamcolle.VERSION)
@Registrant
@RegistrationMod(pkg = "im.kaMColle", res = "kamcolle")
public class Kamcolle {
	public static final String VERSION = "0.0.2";
	public static final String ID="kamcolle";
	public static final Logger LOGGER = LogManager.getLogger(ID);	
	public static KamcolleConfig coreConfig=new KamcolleConfig(new File("config/Kamcolle.cfg"));
	//public static int message_Index=0;
	
	@SidedProxy(
            clientSide = "im.kaMColle.proxy.KamcolleClientProxy",
            serverSide = "im.kaMColle.proxy.KamcolleCommonProxy"
    )
	@RegInit
    public static KamcolleCommonProxy proxy;
	@Instance("kamcolle")
	public static Kamcolle instance;
	public static KamcolleCreativeTab TAB=new KamcolleCreativeTab("kancraft");
	
	@RegBlock
	public static FleetSallyBoard blockSallyBoard=new FleetSallyBoard();
	
	@RegItem
	public static KamcolleFleetCard itemFleetCard=new KamcolleFleetCard();
	@RegMessageHandler.WrapperInstance
	public static final SimpleNetworkWrapper MsgHandler = NetworkRegistry.INSTANCE.newSimpleChannel(Kamcolle.ID);
	

	
	public static void LogInfo(Object obj){
		if(obj==null)
			LOGGER.info("null");
		else
			LOGGER.info(obj);
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		LogInfo("Mod "+ID+"_"+VERSION);
	    RegistrationManager.INSTANCE.registerAll(this, "PreInit");
	}
	 
	@EventHandler
	public void init(FMLInitializationEvent event)
	{	
	    //EntityRegistry.addSpawn(EntityWoodKarakuriNingyG.class, 10, 4, 4, EnumCreatureType.monster);
		RegistrationManager.INSTANCE.registerAll(this, "Init");
	    //GameRegistry.addRecipe(new ItemStack(sth, 1), new Object[]{"XYX",Character.valueOf('X'),s,Character.valueOf('Y'),th});
	}
	 
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		RegistrationManager.INSTANCE.registerAll(this, "PostInit");
	}

}
