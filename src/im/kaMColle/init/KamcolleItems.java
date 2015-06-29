package im.kaMColle.init;

import cpw.mods.fml.common.registry.GameRegistry;
import im.kaMColle.Kamcolle;
import im.kaMColle.item.ItemSallyBoard;
import im.kaMColle.item.KamcolleFleetCard;

public class KamcolleItems {
	public static KamcolleFleetCard fleetCard=new KamcolleFleetCard();
	public static void init(){
		GameRegistry.registerItem(fleetCard, "kancolleFleetCard");
	}
}
