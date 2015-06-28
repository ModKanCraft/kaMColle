package im.kaMColle.handleevent;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class PlayerWithKandouInWaterHandler {
	@SubscribeEvent
	public void onPlayerInWater(LivingUpdateEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player=(EntityPlayer) event.entity;
			if(player.worldObj.isAABBInMaterial(player.boundingBox, Material.water)){
				
				if(!(player.getEntityData().getString("FleetClass").isEmpty()||player.getEntityData().getString("FleetClass").equals("NULL")))floatPlayerOnWater(player);
			}
		}
	}
	public void floatPlayerOnWater(EntityPlayer player){
		int i0=5;
		int i1=0;
		for (int i = 0; i < i0; ++i){
			double d1 = player.boundingBox.minY + (player.boundingBox.maxY - player.boundingBox.minY) * (double)(i + 0) / (double)i0;
			double d3 = player.boundingBox.minY + (player.boundingBox.maxY - player.boundingBox.minY) * (double)(i + 1) / (double)i0;
			AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(player.boundingBox.minX, d1, player.boundingBox.minZ, player.boundingBox.maxX, d3, player.boundingBox.maxZ);
			if (player.worldObj.isAABBInMaterial(axisalignedbb, Material.water)){
				i1 +=1;
			}
		}
		player.motionY+=(double)i1*0.05;
		if(i1==2){
			if(player.motionY<-0.01D||player.motionY>0.01D){
				player.motionY/=2;
			}else{
				player.motionY+=0.01;
			}
		}
	}
	@SubscribeEvent
	public void syncFleetClassOnPlayerConstruct(PlayerEvent.PlayerLoggedInEvent e){
		
	}
}
