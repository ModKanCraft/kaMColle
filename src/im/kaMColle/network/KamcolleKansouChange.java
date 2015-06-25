package im.kaMColle.network;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;


public class KamcolleKansouChange implements IMessage,
IMessageHandler<KamcolleKansouChange, KamcolleKansouChange>{
	int id;
	EntityPlayer player;
	public FleetClass fleetClass;
	public KamcolleKansouChange(){}
	private KamcolleKansouChange(EntityPlayer player){
		this.id=0;
		this.player=player;
	}
	public KamcolleKansouChange(EntityPlayer player,FleetClass Class,int id) {
		this.id=id;
		this.player=player;
		this.fleetClass=Class;
	}
	public static KamcolleKansouChange getSyncPacket(EntityPlayer player){
		return new KamcolleKansouChange(player);
	}
	public static KamcolleKansouChange getSyncReplyPacket(EntityPlayer player,FleetClass Class){
		return new KamcolleKansouChange(player,Class,0);
	}
	public static KamcolleKansouChange getKansouChangePacket(EntityPlayer player,FleetClass Class){
		return new KamcolleKansouChange(player,Class,1);
	}
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		this.id=buf.readInt();
		byte length;
		byte[] bs;
		length=buf.readByte();
		bs=new byte[length];
		for(byte b=0;b<length;b++){
			bs[b]=buf.readByte();
		}
		String name=new String(bs);
		bs=null;
		if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT))
			this.player=Minecraft.getMinecraft().theWorld.getPlayerEntityByName(name);
		else
			getPlayerOnServerByName(name);
		length=buf.readByte();
		if(length==0)return;
		bs=new byte[length];
		for(byte b=0;b<length;b++){
			bs[b]=buf.readByte();
		}
		this.fleetClass=fleetClass.valueOf(new String(bs));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		try{
			buf.writeInt(this.id);
			buf.writeByte(this.player.getDisplayName().length());
			buf.writeBytes(this.player.getDisplayName().getBytes());
			if(this.fleetClass==null){
				buf.writeByte(0);
				return;
			}
			buf.writeByte(this.fleetClass.name().length());
			buf.writeBytes(fleetClass.name().getBytes());
		}catch(NullPointerException e){
			Kamcolle.LogInfo(FMLCommonHandler.instance().getEffectiveSide());
			Kamcolle.LogInfo(this.id);
			Kamcolle.LogInfo(this.player);
		}
	}

	@Override
	public KamcolleKansouChange onMessage(KamcolleKansouChange message, MessageContext ctx) {
		Kamcolle.LogInfo(message.id);
		if(message.player==null)return null;
		if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER)){
			//服务端处理消息
			switch(message.id){
			case 0:
				for(Object o:message.player.worldObj.playerEntities){
					EntityPlayer p=(EntityPlayer)o;
					MessageHandler.INSTANCE.sendTo(
							getSyncReplyPacket(p, getClassName(p)), 
							(EntityPlayerMP) message.player);
				}
				break;
			case 1:
				message.player.getEntityData().setString("FleetClass", message.fleetClass.name());
				for(Object o:message.player.worldObj.playerEntities){
					EntityPlayer p=(EntityPlayer)o;
					MessageHandler.INSTANCE.sendTo(
							getSyncReplyPacket(message.player, message.fleetClass),(EntityPlayerMP) p);
				}
			}
		}else{
			//客户端处理消息
			switch(message.id){
			case 0:
				message.player.getEntityData().setString("FleetClass", message.fleetClass.name());
			}
		}
		return null;
	}
	
	@SubscribeEvent
	public void syncWhilePlayerConstruct(EntityJoinWorldEvent e){
		if(e.entity instanceof EntityPlayer&&(e.entity.worldObj.isRemote)){
			Kamcolle.LogInfo(e);
			MessageHandler.INSTANCE.sendToServer(getSyncPacket((EntityPlayer) e.entity));
		}
	}
	private FleetClass getClassName(EntityPlayer player){
		String ClassName=player.getEntityData().getString("FleetClass").isEmpty()?"NULL":player.getEntityData().getString("FleetClass");
		FleetClass Class=FleetClass.valueOf(ClassName);
		return Class;
	}
	private EntityPlayer getPlayerOnServerByName(String name){
		for(WorldServer w:MinecraftServer.getServer().worldServers){
			if((player=w.getPlayerEntityByName(name))!=null){
				return player;
			}
		}
		return null;
	}
}
