package im.kaMColle.network.packet;

import im.kaMColle.Kamcolle;
import im.kaMColle.network.MessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KansouSync extends PlayerMsg implements IMessageHandler<KansouSync, KansouSyncReplyPacket>{
	public KansouSync(EntityPlayer p) {
		super(p);
	}

	public KansouSync() {
		super();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void catchEntityJoinWorld(EntityJoinWorldEvent e){
		Kamcolle.LogInfo(e.entity);
		if(e.entity instanceof EntityPlayer){
			EntityPlayer p=(EntityPlayer) e.entity;
			MessageHandler.INSTANCE.sendToServer(new KansouSync(p));
		}
	}

	@Override
	public KansouSyncReplyPacket onMessage(KansouSync message, MessageContext ctx) {
		return new KansouSyncReplyPacket(message.player);
	}
}