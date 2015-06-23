package im.kaMColle.network;

import im.kaMColle.FleetClass;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


public class KamcolleGUIMessage implements IMessage,IMessageHandler<KamcolleGUIMessage, IMessage>{
	EntityPlayer player;
	World world;
	public FleetClass fleetClass;
	public KamcolleGUIMessage(EntityPlayer player,FleetClass Class) {
		this.player=player;
		world=player.worldObj;
		this.fleetClass=Class;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		this.world=WorldProvider.getProviderForDimension(buf.readInt()).worldObj;
		byte length;
		byte[] bs;
		length=buf.readByte();
		bs=new byte[length];
		for(byte b=0;b<length;b++){
			bs[b]=buf.readByte();
		}
		String name=new String(bs);
		bs=null;
		this.player=this.world.getPlayerEntityByName(name);
		length=buf.readByte();
		bs=new byte[length];
		for(byte b=0;b<length;b++){
			bs[b]=buf.readByte();
		}
		this.fleetClass=fleetClass.valueOf(new String(bs));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeInt(this.world.provider.dimensionId);
		buf.writeByte(this.player.getDisplayName().length());
		buf.writeBytes(this.player.getDisplayName().getBytes());
		buf.writeByte(this.fleetClass.name().length());
		buf.writeBytes(fleetClass.name().getBytes());
	}

	@Override
	public IMessage onMessage(KamcolleGUIMessage message, MessageContext ctx) {
		if(!message.fleetClass.equals(FleetClass.NULL))
			message.player.getEntityData().setString("FleetClass", message.fleetClass.name());
		else
			message.player.getEntityData().removeTag("FleetClass");
		// TODO Auto-generated method stub
		return null;
	}
}
