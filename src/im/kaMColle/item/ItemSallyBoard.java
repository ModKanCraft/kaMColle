package im.kaMColle.item;

import im.kaMColle.Kamcolle;
import net.minecraft.item.ItemBlock;

public class ItemSallyBoard extends ItemBlock {
	public ItemSallyBoard(){
		super(Kamcolle.blockSallyBoard);
		this.setMaxStackSize(4);
	}
}
