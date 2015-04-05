package im.kaMColle;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class KamcolleCreativeTab extends CreativeTabs{

	/**
     * @param label Tab名称
     */
	public KamcolleCreativeTab(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getTabIconItemIndex() {
		return Block.pumpkin.blockID;
	}


}
