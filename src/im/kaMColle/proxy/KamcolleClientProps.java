package im.kaMColle.proxy;

import im.kaMColle.Kamcolle;
import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegInit;
@Registrant
@RegInit
public class KamcolleClientProps {
	
	public static int ANIME_SPEED;

	public static void init(){
		ANIME_SPEED=Kamcolle.coreConfig.getGeneralIntegerProperties("GUI_ANIME_SPEED",30);
	}
}
