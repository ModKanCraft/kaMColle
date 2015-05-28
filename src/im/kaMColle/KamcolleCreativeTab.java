package im.kaMColle;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class KamcolleCreativeTab extends CreativeTabs{

	/**
     * @param label Tab名称
     */
	public KamcolleCreativeTab(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Item getTabIconItem(){
		return Items.boat;
	}


}
