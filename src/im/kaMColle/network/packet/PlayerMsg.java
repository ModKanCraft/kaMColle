package im.kaMColle.network.packet;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PlayerMsg extends KamcolleCustomMessage {
	public EntityPlayer player;
	public PlayerMsg(EntityPlayer player){
		this.player=player;
		this.strings.add(this.player.getDisplayName());
	}
	public PlayerMsg() {}
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
	}
	public void fromBytes(ByteBuf buf){
		super.fromBytes(buf);
		Side s=FMLCommonHandler.instance().getEffectiveSide();
		this.player=Kamcolle.proxy.getPlayer(this.strings.get(0));
	}
}
