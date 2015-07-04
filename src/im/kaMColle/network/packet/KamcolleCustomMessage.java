package im.kaMColle.network.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class KamcolleCustomMessage implements IMessage {
	boolean[] bools;
	int[] ints;
	byte[] bytes;
	String[] strings;
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		bools=new boolean[buf.readByte()];
		ints=new int[buf.readByte()];
		bytes=new byte[buf.readByte()];
		strings=new String[buf.readByte()];
		for(int i=0;i<bools.length;i++){
			bools[i]=buf.readBoolean();
		}
		for(int i=0;i<ints.length;i++){
			ints[i]=buf.readInt();
		}
		for(int i=0;i<bytes.length;i++){
			bytes[i]=buf.readByte();
		}
		for(int i=0;i<strings.length;i++){
			byte length=buf.readByte();
			byte[] bs=new byte[length];	
			strings[i]=new String(buf.readBytes(length).array());
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeByte(bools.length);
		buf.writeByte(ints.length);
		buf.writeByte(bytes.length);
		buf.writeByte(strings.length);
		for(int i=0;i<bools.length;i++){
			buf.writeBoolean(bools[i]);
		}
		for(int i=0;i<ints.length;i++){
			buf.writeInt(ints[i]);
		}
		for(int i=0;i<bytes.length;i++){
			buf.writeByte(bytes[i]);
		}
		for(int i=0;i<strings.length;i++){
			byte[] bs=strings[i].getBytes();
			buf.writeByte(bs.length);
			buf.writeBytes(bs);
			strings[i]=new String(bs);
		}
	}

}
