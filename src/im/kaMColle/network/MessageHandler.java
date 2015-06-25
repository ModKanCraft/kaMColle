package im.kaMColle.network;

import im.kaMColle.Kamcolle;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class MessageHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Kamcolle.ID);

	public static void init() {
		int idx = 0;
		INSTANCE.registerMessage(KamcolleKansouChange.class, KamcolleKansouChange.class, idx++, Side.SERVER);
		INSTANCE.registerMessage(KamcolleKansouChange.class, KamcolleKansouChange.class, idx++, Side.CLIENT);
		
	}
}
