package im.kaMColle.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import im.kaMColle.network.MessageHandler;
import net.minecraft.entity.player.EntityPlayer;

public class KansouChangePacket extends PlayerAndFleetClassMsg implements IMessageHandler<KansouChangePacket, IMessage>{
	public KansouChangePacket(){super();}
	public KansouChangePacket(EntityPlayer player, FleetClass Class) {
		super(player, Class);
	}

	@Override
	public IMessage onMessage(KansouChangePacket message, MessageContext ctx) {
		message.player.getEntityData().setString("FleetClass", message.fleetClass.name());
		Kamcolle.LogInfo(message.player.getEntityData().getString("FleetClass"));
		MessageHandler.INSTANCE.sendToAll(new KansouSyncReplyPacket(message.player));
		return null;
	}
}
