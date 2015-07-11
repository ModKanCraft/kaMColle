package im.kaMColle.network.packet;

import im.kaMColle.Kamcolle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ReportedException;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class KansouControlMessage extends PlayerMsg implements IMessageHandler<KansouControlMessage, IMessage>{
	public byte flow=0;
	public KansouControlMessage(){super();}
	public KansouControlMessage(EntityPlayer player,byte flow){
		// TODO Auto-generated constructor stub
		super(player);
		this.flow=flow;
		
		this.bytes.add(this.flow);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		super.toBytes(buf);
	}
	public void fromBytes(ByteBuf buf){
		super.fromBytes(buf);
		this.flow=this.bytes.get(0);
	}
	@Override
	public IMessage onMessage(KansouControlMessage message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}
}
