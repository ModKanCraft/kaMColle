package im.kaMColle.item;

import im.kaMColle.Kamcolle;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemSallyBoard extends ItemBlock {
	public ItemSallyBoard(Block block){
		super(block);
		this.setMaxStackSize(6);
	}
}
