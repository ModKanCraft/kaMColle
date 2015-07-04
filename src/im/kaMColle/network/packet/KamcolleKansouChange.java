package im.kaMColle.network.packet;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import im.kaMColle.GUI.KansouChangeGUI;
import im.kaMColle.proxy.KamcolleCommonProxy;
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
import cpw.mods.fml.relauncher.SideOnly;


public class KamcolleKansouChange extends KamcolleCustomMessage{
	public byte id;
	public EntityPlayer player;
	public FleetClass fleetClass;
	private KamcolleKansouChange(EntityPlayer player,byte id){
		this.id=id;
		this.player=player;
	}
	public KamcolleKansouChange(EntityPlayer player,FleetClass Class,byte id) {
		this.id=id;
		this.player=player;
		this.fleetClass=Class;
	}
	public static KamcolleKansouChange getSyncPacket(EntityPlayer player){
		return new KamcolleKansouChange(player,(byte) 0);
	}
	public static KamcolleKansouChange getSyncReplyPacket(EntityPlayer player){
		return new KamcolleKansouChange(player,getFleetClass(player),(byte) 0);
	}
	public static KamcolleKansouChange getKansouChangePacket(EntityPlayer player,FleetClass Class){
		return new KamcolleKansouChange(player,Class,(byte) 1);
	}
	public static KamcolleKansouChange getGUIDisplayPacket(EntityPlayer p){
		return new KamcolleKansouChange(p,(byte) 1);
	}

	public void toBytes(ByteBuf buf) {
		this.bytes=new byte[1];
		this.bytes[0]=this.id;
		this.strings=new String[2];
		this.strings[0]=this.player.getDisplayName();
		this.strings[1]=this.fleetClass.name();
		super.toBytes(buf);
	}
	public void fromBytes(ByteBuf buf){
		super.fromBytes(buf);
		this.id=this.bytes[0];
		this.player=Kamcolle.proxy.getPlayer(this.strings[0]);
		this.fleetClass=FleetClass.valueOf(this.strings[1]);
	}
	private static FleetClass getFleetClass(EntityPlayer player){
		String ClassName=player.getEntityData().getString("FleetClass").isEmpty()?"NULL":player.getEntityData().getString("FleetClass");
		FleetClass Class=FleetClass.valueOf(ClassName);
		return Class;
	}
}
