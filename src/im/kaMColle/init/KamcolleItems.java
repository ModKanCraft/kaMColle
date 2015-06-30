package im.kaMColle.init;

import im.kaMColle.item.KamcolleFleetCard;
import cpw.mods.fml.common.registry.GameRegistry;

public class KamcolleItems {
	public static KamcolleFleetCard kamcolleFleetCard = new KamcolleFleetCard();
	public static void init(){
		GameRegistry.registerItem(kamcolleFleetCard, "kamcolleFleetCard");
	}
}
