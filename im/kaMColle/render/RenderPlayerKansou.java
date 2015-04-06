package im.kaMColle.render;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.Specials;

public class RenderPlayerKansou {
	ModelBat model=new ModelBat();
	RenderManager renderManager=RenderManager.instance;
	/*[C]LI-狼:
	 *所以如果你要渲染世界里其他玩家的效果，还得自己处理下变换，不过也不难
	 *GL11.glTranslated(
	 *otherPlayer.posX - RenderManager.renderPosX, 
	 *otherPlayer.posY - RenderManager.renderPosY, 
	 *otherPlayer.posZ - RenderManager.renderPosZ);
	 */
	@ForgeSubscribe
	public void renderPlayerKansouTest1(RenderPlayerEvent.Post event){
		if(event.entityPlayer.getHeldItem()==null||event.entityPlayer.getHeldItem().itemID!=Item.boat.itemID){
			return;
		}
		double pos[]={event.entityPlayer.posX,event.entityPlayer.posY,event.entityPlayer.posZ};
		float otherPlayerYaw=event.entity.rotationYaw;
		float viewX=renderManager.playerViewX;
		float viewY=renderManager.playerViewY;
		float angle=otherPlayerYaw-viewX;
		GL11.glTranslated(
				pos[0] - renderManager.renderPosX, 
				pos[1] - renderManager.renderPosY, 
				pos[2] - renderManager.renderPosZ);
		GL11.glRotatef(-viewY, 1F, 0F, 0F);
		GL11.glRotatef(angle, 0F, 0F, 1F);
		model.render((Entity)null,0F,0F,3F, 0F, 0F, 1F);
	}
	@ForgeSubscribe
	public void renderPlayerKansouTest2(RenderPlayerEvent.Specials.Post event){
		if(event.entityPlayer.getHeldItem()==null||event.entityPlayer.getHeldItem().itemID!=Item.bed.itemID){
			return;
		}
		model.render((Entity)null,0F,0F,3F, 0F, 0F, 1F);
	}
}
