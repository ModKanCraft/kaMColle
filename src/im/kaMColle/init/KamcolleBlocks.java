package im.kaMColle.init;

import cpw.mods.fml.common.registry.GameRegistry;
import im.kaMColle.Kamcolle;
import im.kaMColle.item.ItemSallyBoard;

public class KamcolleBlocks {
	public static void init(){
		GameRegistry.registerBlock(Kamcolle.blockSallyBoard,ItemSallyBoard.class, "sallyboard");
	}
}
