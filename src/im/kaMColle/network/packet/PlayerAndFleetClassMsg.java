package im.kaMColle.network.packet;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PlayerAndFleetClassMsg extends PlayerMsg {
	public FleetClass fleetClass;
	public PlayerAndFleetClassMsg(EntityPlayer player, FleetClass Class) {
		super(player);
		this.fleetClass=Class;
		
		this.strings.add(this.fleetClass.name());
	}
	public PlayerAndFleetClassMsg() {super();}
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
	}
	public void fromBytes(ByteBuf buf){
		super.fromBytes(buf);
		this.fleetClass=FleetClass.valueOf(this.strings.get(1));
	}
}
