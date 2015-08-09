package im.kaMColle.proxy;

import org.lwjgl.input.Keyboard;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegInit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import im.kaMColle.Kamcolle;
import im.kaMColle.anno.RegKeyBingding;
import net.minecraft.client.settings.KeyBinding;
@Registrant
@RegInit
@SideOnly(Side.CLIENT)
public class KamcolleClientProps {
	
	public static int ANIME_SPEED;
	@RegKeyBingding
	public static KeyBinding aim =new KeyBinding(Kamcolle.ID+".key.aim", Keyboard.KEY_LMENU, Kamcolle.ID+".title");

	public static void init(){
		ANIME_SPEED=Kamcolle.coreConfig.getGeneralIntegerProperties("GUI_ANIME_SPEED",30);

	}
}
