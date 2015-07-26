package im.kaMColle.network.packet;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegMessageHandler;
import im.kaMColle.FleetClass;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
@Registrant
@RegMessageHandler(msg = KansouSyncReplyPacket.class, side = RegMessageHandler.Side.CLIENT)
public class KansouSyncReplyPacket extends PlayerAndFleetClassMsg implements IMessageHandler<KansouSyncReplyPacket, IMessage>{
	public KansouSyncReplyPacket(){super();}
	public KansouSyncReplyPacket(EntityPlayer player) {
		super(player,FleetClass.valueOf(player.getEntityData().getString("FleetClass").isEmpty()?"NULL":player.getEntityData().getString("FleetClass")));
	}

	@Override
	public IMessage onMessage(KansouSyncReplyPacket message, MessageContext ctx) {
		// TODO Auto-generated method stub
		message.player.getEntityData().setString("FleetClass", message.fleetClass.name());
		return null;
	}
}

