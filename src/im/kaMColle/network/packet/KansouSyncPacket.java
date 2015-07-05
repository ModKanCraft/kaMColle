package im.kaMColle.network.packet;

import im.kaMColle.Kamcolle;
import im.kaMColle.network.MessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KansouSyncPacket extends PlayerMsg implements IMessageHandler<KansouSyncPacket, IMessage>{
	public KansouSyncPacket(){super();}
	public KansouSyncPacket(EntityPlayer player){
		super(player);
	}

	@Override
	public IMessage onMessage(KansouSyncPacket message, MessageContext ctx) {
		for(Object o:message.player.worldObj.playerEntities){
			MessageHandler.INSTANCE.sendTo(new KansouSyncReplyPacket(message.player), (EntityPlayerMP) message.player);
		}
		return null;
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void catchEntityJoinWorld(EntityJoinWorldEvent e){
		MessageHandler.INSTANCE.sendToServer(new KansouSyncPacket(Minecraft.getMinecraft().thePlayer));
	}
}