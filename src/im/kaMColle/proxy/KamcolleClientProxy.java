package im.kaMColle.proxy;

import java.io.File;

import org.lwjgl.input.Keyboard;

import cn.annoreg.core.Registrant;
import cn.liutils.util.helper.KeyManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import im.kaMColle.Kamcolle;
import im.kaMColle.anno.RegKeyBingding;
import im.kaMColle.config.KamcolleConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.config.Configuration;
@Registrant
public class KamcolleClientProxy extends KamcolleCommonProxy{
	public static Configuration keyConfig=new Configuration(new File("config/"+Kamcolle.ID+"Key.cfg"));
	public void preInit() {
		// TODO Auto-generated method stub
		super.preInit();
	}

	public void init() {
		// TODO Auto-generated method stub
		super.init();
		registerRenderThings();
		registerSound();
	}

	public void postInit() {
		// TODO Auto-generated method stub
		super.postInit();
	}

	public void registerRenderThings() {
		// TODO Auto-generated method stub
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
}
