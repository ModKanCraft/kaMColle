package im.kaMColle.network.packet;

import im.kaMColle.Kamcolle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ReportedException;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class KansouControlMessage implements IMessage,
		IMessageHandler<KansouControlMessage, IMessage> {
	public byte flow=0;
	public EntityPlayer player;
	public KansouControlMessage() {
		// TODO Auto-generated constructor stub
	}
	public KansouControlMessage(EntityPlayer player,byte flow) {
		// TODO Auto-generated constructor stub
		this.player=player;
		this.flow=flow;
	}
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		this.flow=buf.readByte();
		byte length;
		byte[] bs;
		length=buf.readByte();
		bs=new byte[length];
		for(byte b=0;b<length;b++){
			bs[b]=buf.readByte();
		}
		String name=new String(bs);
		this.player=Kamcolle.proxy.getPlayer(name);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeByte(flow);
		byte[] bs=this.player.getDisplayName().getBytes();
		buf.writeByte(bs.length);
		buf.writeBytes(bs);
	}

	@Override
	public IMessage onMessage(KansouControlMessage message, MessageContext ctx) {
		// TODO Auto-generated method stub
		try{
			player.getDataWatcher().updateObject(25, Byte.valueOf(this.flow));
		}catch(ReportedException e){
			player.getDataWatcher().addObject(25, Byte.valueOf(this.flow));
		}
		return null;
	}

}
