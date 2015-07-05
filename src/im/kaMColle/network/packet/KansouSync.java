package im.kaMColle.network.packet;

import im.kaMColle.Kamcolle;
import im.kaMColle.network.MessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KansouSync{
	@SubscribeEvent
	public void catchEntityJoinWorld(EntityJoinWorldEvent e){
		Kamcolle.LogInfo(e.entity);
		if(e.entity instanceof EntityPlayer&&!e.entity.worldObj.isRemote){
			EntityPlayer p=(EntityPlayer) e.entity;
			for(Object o:p.worldObj.playerEntities){
				MessageHandler.INSTANCE.sendTo(new KansouSyncReplyPacket(p), (EntityPlayerMP)p);
			}
		}
	}
}