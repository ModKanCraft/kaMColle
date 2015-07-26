package im.kaMColle.network.packet;

import im.kaMColle.Kamcolle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegEventHandler;
import cn.annoreg.mc.RegMessageHandler;
import cn.annoreg.mc.RegEventHandler.Bus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
@Registrant
@RegEventHandler(Bus.Forge)
@RegMessageHandler(msg = KansouSyncPacket.class, side = RegMessageHandler.Side.SERVER)
public class KansouSyncPacket extends PlayerMsg implements IMessageHandler<KansouSyncPacket, KansouSyncReplyPacket>{
	public KansouSyncPacket(EntityPlayer entity) {
		// TODO Auto-generated constructor stub
		super(entity);
	}

	public KansouSyncPacket() {
		// TODO Auto-generated constructor stub
		super();
	}

	@SubscribeEvent
	public void catchEntityJoinWorld(EntityJoinWorldEvent e){
		if(e.entity instanceof EntityPlayer){
			if(e.entity.worldObj.isRemote){
				Kamcolle.MsgHandler.sendToServer(new KansouSyncPacket((EntityPlayer) e.entity));
			}
		}
	}

	@Override
	public KansouSyncReplyPacket onMessage(KansouSyncPacket message,
			MessageContext ctx) {
		// TODO Auto-generated method stub
		Kamcolle.LogInfo(message.player.getEntityData().getString("FleetClass"));
		return new KansouSyncReplyPacket(message.player);
	}
}