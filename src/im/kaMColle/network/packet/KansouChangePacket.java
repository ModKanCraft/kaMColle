package im.kaMColle.network.packet;

import org.apache.logging.log4j.Level;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import im.kaMColle.tileEntity.SallyBoardTileEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
@Registrant
@RegMessageHandler(msg = KansouChangePacket.class, side = RegMessageHandler.Side.SERVER)
public class KansouChangePacket extends PlayerAndFleetClassMsg implements IMessageHandler<KansouChangePacket, IMessage>{
	int blockX;
	int blockY;
	int blockZ;
	public KansouChangePacket(){super();}
	public KansouChangePacket(EntityPlayer player, FleetClass Class,TileEntity blockTile) {
		 this(player,Class,blockTile.xCoord,blockTile.yCoord,blockTile.zCoord);
	}
	public KansouChangePacket(EntityPlayer player, FleetClass Class,int x,int y,int z){
		super(player,Class);
		this.blockX=x;
		this.blockY=y;
		this.blockZ=z;
	}
	public void fromBytes(ByteBuf buf){
		super.fromBytes(buf);
		this.blockX=this.ints.get(0);
		this.blockY=this.ints.get(1);
		this.blockZ=this.ints.get(2);
	}

	public void toBytes(ByteBuf buf){
		this.ints.add(this.blockX);
		this.ints.add(this.blockY);
		this.ints.add(this.blockZ);
		super.toBytes(buf);
	}
	@Override
	public IMessage onMessage(KansouChangePacket message, MessageContext ctx) {
		message.player.getEntityData().setString("FleetClass", message.fleetClass.name());
		Kamcolle.LogInfo(message.player.getEntityData().getString("FleetClass"));
		Kamcolle.MsgHandler.sendToAll(new KansouSyncReplyPacket(message.player));
		SallyBoardTileEntity t = (SallyBoardTileEntity) message.player.worldObj
				.getTileEntity(message.blockX, message.blockY,
						message.blockZ);
		try {
			t.isOccupied = false;
		} catch (NullPointerException e) {
			String s=String.format(("Illegal coords for BlockSallyBoard.The Block on this coord %d,%d,%d is %s"),
					message.blockX,
					message.blockY,
					message.blockZ,
					message.player.worldObj.getBlock(message.blockX, message.blockY, message.blockZ).getUnlocalizedName());
			Kamcolle.LOGGER.log(Level.ERROR, s);
			e.printStackTrace();
		}
		return null;
	}
}
