package im.kaMColle.network;

import im.kaMColle.Kamcolle;
import im.kaMColle.network.packet.KansouChangePacket;
import im.kaMColle.network.packet.KansouControlMessage;
import im.kaMColle.network.packet.KansouSync;
import im.kaMColle.network.packet.KansouSyncReplyPacket;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class MessageHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Kamcolle.ID);

	public static void init() {
		int idx = 0;
		INSTANCE.registerMessage(KansouChangePacket.class, KansouChangePacket.class, idx++, Side.SERVER);
		INSTANCE.registerMessage(KansouSyncReplyPacket.class, KansouSyncReplyPacket.class, idx++, Side.CLIENT);
		INSTANCE.registerMessage(KansouControlMessage.class, KansouControlMessage.class, idx++, Side.SERVER);
	}
}
