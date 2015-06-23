package im.kaMColle.network;

import java.io.UnsupportedEncodingException;

import im.kaMColle.FleetClass;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


public class KamcolleGUIMessage implements IMessage,IMessageHandler<KamcolleGUIMessage, IMessage>{
	int playerID;
	public FleetClass fleetClass;
	public KamcolleGUIMessage(int ID,FleetClass Class) {
		this.playerID=ID;
		this.fleetClass=Class;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		this.playerID=buf.readInt();
		byte length=buf.readByte();
		byte[] bs=new byte[length];
		for(byte b=0;b<length;b++){
			bs[b]=buf.readByte();
		}
		this.fleetClass=fleetClass.valueOf(new String(bs));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeInt(this.playerID);
		buf.writeByte(this.fleetClass.name().length());
		buf.writeBytes(fleetClass.name().getBytes());
	}

	@Override
	public IMessage onMessage(KamcolleGUIMessage message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}
}
