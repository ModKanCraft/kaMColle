package im.kaMColle.network.packet;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegMessageHandler;
import im.kaMColle.Kamcolle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ReportedException;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
@Registrant
@RegMessageHandler(msg = KansouControlMessage.class, side = RegMessageHandler.Side.SERVER)
public class KansouControlMessage extends PlayerMsg implements IMessageHandler<KansouControlMessage, IMessage>{
	private static byte WatchedID;
	public byte flotage=0;
	public KansouControlMessage(){
		super();
		WatchedID=(byte)Kamcolle.coreConfig.getCoreIntProps("playerDataWach.check", 25, "Check player's dataWatcher 25 if it has been occupied,or throw the Excption");
	}
	public KansouControlMessage(EntityPlayer player,byte flotage){
		// TODO Auto-generated constructor stub
		super(player);
		this.flotage=flotage;
		
		this.bytes.add(this.flotage);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		super.toBytes(buf);
	}
	public void fromBytes(ByteBuf buf){
		super.fromBytes(buf);
		this.flotage=this.bytes.get(0);
	}
	@Override
	public IMessage onMessage(KansouControlMessage message, MessageContext ctx) {
		// TODO Auto-generated method stub
		try{
			message.player.getDataWatcher().updateObject(WatchedID, Byte.valueOf(message.flotage));
		}catch(Exception e){
			try{
				message.player.getDataWatcher().addObject(WatchedID, Byte.valueOf((byte) 0));
			}catch(Exception e0){
				throw e0;
			}
		}
		return null;
	}
	public static byte getWatchedID(){
		return WatchedID;
	}
}
