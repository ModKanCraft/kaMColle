package im.kaMColle.proxy;

import im.kaMColle.Kamcolle;

public class KamcolleClientProps {
	
	public static int ANIME_SPEED;

	public static void init(){
		ANIME_SPEED=Kamcolle.coreConfig.getGeneralIntegerProperties("GUI_ANIME_SPEED", 60);
	}
}
