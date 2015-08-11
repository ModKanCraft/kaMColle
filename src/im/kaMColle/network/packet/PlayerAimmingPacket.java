package im.kaMColle.network.packet;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegMessageHandler;
import cn.annoreg.mc.RegMessageHandler.Side;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
@Registrant
@RegMessageHandler(msg = PlayerAimmingPacket.class, side = Side.SERVER)
public class PlayerAimmingPacket extends PlayerMsg implements IMessageHandler<PlayerAimmingPacket,IMessage>{
	public PlayerAimmingPacket() {
		// TODO Auto-generated constructor stub
		super();
	}
	public PlayerAimmingPacket(EntityPlayer p){
		super(p);
	}
	@Override
	public IMessage onMessage(PlayerAimmingPacket message, MessageContext ctx) {
		// TODO Auto-generated method stub
		byte b;
		try{
			b=(byte) (message.player.getDataWatcher().getWatchableObjectByte(26)==(byte)0?1:0);
		}catch(Exception e){
			message.player.getDataWatcher().addObject(26,Byte.valueOf((byte)0));
			b=(byte)1;
		}
		message.player.getDataWatcher().updateObject(26, Byte.valueOf(b));
		return null;
	}
}
