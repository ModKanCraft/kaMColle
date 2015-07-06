package im.kaMColle.network.packet;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PlayerMsg extends KamcolleCustomMessage {
	public EntityPlayer player;
	public PlayerMsg(EntityPlayer player){
		this.player=player;
	}
	public PlayerMsg() {}
	public void toBytes(ByteBuf buf) {
		this.strings=new String[1];
		this.strings[0]=this.player.getDisplayName();
		super.toBytes(buf);
	}
	public void fromBytes(ByteBuf buf){
		super.fromBytes(buf);
		this.player=Kamcolle.proxy.getPlayer(this.strings[0]);
	}
}
