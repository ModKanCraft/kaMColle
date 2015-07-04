package im.kaMColle.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import im.kaMColle.network.packet.KamcolleKansouChange;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class KansouMessageServerHandler implements IMessageHandler<KamcolleKansouChange, KamcolleKansouChange> {

	@Override
	public KamcolleKansouChange onMessage(KamcolleKansouChange message,
			MessageContext ctx) {
		// TODO Auto-generated method stub
		switch(message.id){
		case 0:
			for(Object o:message.player.worldObj.playerEntities){
				MessageHandler.INSTANCE.sendTo(KamcolleKansouChange.getSyncReplyPacket((EntityPlayer) o), (EntityPlayerMP) message.player);
			}
			break;
		case 1:
			message.player.getEntityData().setString("FleetClass", message.fleetClass.name());
			MessageHandler.INSTANCE.sendToAll(KamcolleKansouChange.getSyncReplyPacket(message.player));
			break;
		}
		return null;
	}

}
