package im.kaMColle.network;

import im.kaMColle.Kamcolle;
import im.kaMColle.network.packet.KamcolleKansouChange;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class MessageHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Kamcolle.ID);

	public static void init() {
		int idx = 0;
		INSTANCE.registerMessage(KansouMessageServerHandler.class, KamcolleKansouChange.class, idx++, Side.SERVER);
		INSTANCE.registerMessage(KansouMessageClientHandler.class, KamcolleKansouChange.class, idx++, Side.CLIENT);
		//INSTANCE.registerMessage(KansouControlMessage.class, KansouControlMessage.class, idx++, Side.SERVER);
	}
}
