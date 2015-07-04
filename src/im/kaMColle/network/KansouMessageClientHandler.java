package im.kaMColle.network;

import im.kaMColle.Kamcolle;
import im.kaMColle.GUI.KansouChangeGUI;
import im.kaMColle.network.packet.KamcolleKansouChange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KansouMessageClientHandler implements IMessageHandler<KamcolleKansouChange, KamcolleKansouChange> {

	@SideOnly(Side.CLIENT)
	public KamcolleKansouChange onMessage(KamcolleKansouChange message,
			MessageContext ctx) {
		switch(message.id){
		case 0:
			message.player.getEntityData().setString("FleetClass", message.fleetClass.name());
			break;
		case 1:
			Kamcolle.proxy.displayGUI(new KansouChangeGUI(message.player));;
			break;
		}
		return null;
	}
	
	@SubscribeEvent
	public void syncWhilePlayerConstruct(EntityJoinWorldEvent e){
		if(e.entity instanceof EntityPlayer){
			Kamcolle.LogInfo(e);
			MessageHandler.INSTANCE.sendToServer(KamcolleKansouChange.getSyncPacket((EntityPlayerMP) e.entity));
		}
	}
}
