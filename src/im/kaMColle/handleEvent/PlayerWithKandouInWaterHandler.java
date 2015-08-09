package im.kaMColle.handleEvent;



import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegEventHandler;
import cn.annoreg.mc.RegEventHandler.Bus;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import im.kaMColle.Kamcolle;
import im.kaMColle.network.packet.KansouControlMessage;

@Registrant
@RegEventHandler(Bus.Forge)
public class PlayerWithKandouInWaterHandler {
	@SubscribeEvent
	public void onPlayerInWater(LivingUpdateEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player=(EntityPlayer) event.entity;
			boolean isInWater=player.worldObj.isAABBInMaterial(player.boundingBox, Material.water);
			if(isInWater){
				String className=player.getEntityData().getString("FleetClass");
				if(!(className.isEmpty()||className.equals("NULL"))){
					if(className.equals("SS")||className.equals("SSV")){
						classSSFloatInWater(player);
					}else{
						floatOnWater(player);
					}
				}
			}
			player.getEntityData().setBoolean("isInWater", isInWater);
		}
	}
	public void floatOnWater(EntityPlayer player){
		int i0=5;
		int i1=0;
		for (int i = 0; i < i0; ++i){
			double d1 = player.boundingBox.minY + (player.boundingBox.maxY - player.boundingBox.minY) * (double)(i + 0) / (double)i0;
			double d3 = player.boundingBox.minY + (player.boundingBox.maxY - player.boundingBox.minY) * (double)(i + 1) / (double)i0;
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(player.boundingBox.minX, d1, player.boundingBox.minZ, player.boundingBox.maxX, d3, player.boundingBox.maxZ);
			if (player.worldObj.isAABBInMaterial(aabb, Material.water)){
				i1 +=1;
			}
		}
		player.motionY+=(double)i1*0.05;
		if(i1==2){
			if(player.motionY<-0.01D||player.motionY>0.01D){
				player.motionY/=2;
			}else{
				player.motionY+=0.0055D;
			}
		}
	}
	public void classSSFloatInWater(EntityPlayer player){
		if(player.motionY<-0.1d)player.motionY=-0.1d;
		int i1=0;
		for(int i=1;i<=2;i++){
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(player.boundingBox.minX, player.boundingBox.minY + (player.boundingBox.maxY - player.boundingBox.minY)*(double)(i-1)/5d, player.boundingBox.minZ, player.boundingBox.maxX, player.boundingBox.minY + (player.boundingBox.maxY - player.boundingBox.minY) *(double)i/5d, player.boundingBox.maxZ);
			if (player.worldObj.isAABBInMaterial(aabb, Material.water))i1+=1;
		}
		if(i1!=0){
			player.motionY+=0.05d*i1;
			byte b;
			try{
				b=(byte) (player.getDataWatcher().getWatchableObjectByte(KansouControlMessage.getWatchedID())-40);
				Kamcolle.LogInfo(b);
			}catch(Exception e){
				b=0;
				player.getDataWatcher().addObject(KansouControlMessage.getWatchedID(),Byte.valueOf((byte) 0));
			}
			player.motionY+=0.002D*b;
		}else{
			try{
				player.getDataWatcher().updateObject(KansouControlMessage.getWatchedID(),Byte.valueOf((byte) 0));
			}catch(Exception e){
				player.getDataWatcher().addObject(KansouControlMessage.getWatchedID(),Byte.valueOf((byte) 0));
			}
		}
	}

}
