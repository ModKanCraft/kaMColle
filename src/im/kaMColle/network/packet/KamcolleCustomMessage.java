package im.kaMColle.network.packet;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public abstract class KamcolleCustomMessage implements IMessage {
	ArrayList<Boolean> bools=new ArrayList<Boolean>();
	ArrayList<Integer> ints=new ArrayList<Integer>();
	ArrayList<Byte> bytes=new ArrayList<Byte>();
	ArrayList<String> strings=new ArrayList<String>();
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		int boolsL=buf.readByte();
		int intsL=buf.readByte();
		int bytesL=buf.readByte();
		int stringsL=buf.readByte();
		for(int i=0;i<boolsL;i++){
			bools.add(i,buf.readBoolean());
		}
		for(int i=0;i<intsL;i++){
			ints.add(i,buf.readInt());
		}
		for(int i=0;i<bytesL;i++){
			bytes.add(i,buf.readByte());
		}
		for(int i=0;i<stringsL;i++){
			byte length=buf.readByte();
			byte[] bs=new byte[length];	
			strings.add(i, new String(buf.readBytes(length).array()));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeByte(bools.size());
		buf.writeByte(ints.size());
		buf.writeByte(bytes.size());
		buf.writeByte(strings.size());
		for(int i=0;i<bools.size();i++){
			buf.writeBoolean(bools.get(i));
		}
		for(int i=0;i<ints.size();i++){
			buf.writeInt(ints.get(i));
		}
		for(int i=0;i<bytes.size();i++){
			buf.writeByte(bytes.get(i));
		}
		for(int i=0;i<strings.size();i++){
			byte[] bs=strings.get(i).getBytes();
			buf.writeByte(bs.length);
			buf.writeBytes(bs);
		}
	}
}
