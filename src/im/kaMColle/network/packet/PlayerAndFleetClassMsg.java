package im.kaMColle.network.packet;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PlayerAndFleetClassMsg extends KamcolleCustomMessage {
	public EntityPlayer player;
	public FleetClass fleetClass;
	public PlayerAndFleetClassMsg(EntityPlayer player, FleetClass Class) {
		this.player=player;
		this.fleetClass=Class;
	}
	public PlayerAndFleetClassMsg() {super();}
	public void toBytes(ByteBuf buf) {
		this.strings=new String[2];
		this.strings[0]=this.player.getDisplayName();
		this.strings[1]=this.fleetClass.name();
		super.toBytes(buf);
	}
	public void fromBytes(ByteBuf buf){
		super.fromBytes(buf);
		this.player=Kamcolle.proxy.getPlayer(this.strings[0]);
		this.fleetClass=FleetClass.valueOf(this.strings[1]);
	}
}
