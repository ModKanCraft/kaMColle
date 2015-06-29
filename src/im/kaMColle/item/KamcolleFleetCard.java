package im.kaMColle.item;

import im.kaMColle.Kamcolle;
import net.minecraft.item.Item;

public class KamcolleFleetCard extends Item {
	public KamcolleFleetCard(){
		this.setMaxStackSize(1);
		this.setUnlocalizedName("kancolleFleetCard");
		this.setNoRepair();
		this.setCreativeTab(Kamcolle.TAB);
	}
}
