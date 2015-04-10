package im.kaMColle.render;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

public class RenderPlayerKansou {
	ModelBoat model=new ModelBoat();
	RenderManager renderManager=RenderManager.instance;
	/*[C]LI-狼:
	 *所以如果你要渲染世界里其他玩家的效果，还得自己处理下变换，不过也不难
	 *GL11.glTranslated(
	 *otherPlayer.posX - RenderManager.renderPosX, 
	 *otherPlayer.posY - RenderManager.renderPosY, 
	 *otherPlayer.posZ - RenderManager.renderPosZ);
	 */
	 /*
	  * 好吧其实我没能自己实现这玩意
	  * 但是Test2就不用我自己实现啊，果断以后用Test2
	  * 记得把这个改回去
	  * ModelBat->entitybat.getIsBatHanging()
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
	}*/
	@ForgeSubscribe
	public void renderPlayerKansouTest2(RenderPlayerEvent.Specials.Post event){
		if(event.entityPlayer.inventory.hasItem(Item.boat.itemID)){
			return;
		}
		BufferedReader br1;
		BufferedReader br2;
		renderManager.renderEngine.bindTexture(new ResourceLocation("textures/entity/boat.png"));
		model.render((Entity)null,0F,-1F,3F, 0F, 0F, 0.0625F);
		try {
			br1=new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/im/kaMColle/OBJmodels/Test.obj")));
			br2=new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("../OBJmodels/Test.obj")));
			
			System.out.println("br1:"+br1.readLine());
			System.out.println("br2:"+br2.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MinecraftForge.EVENT_BUS.unregister(this);
		}
	}
}
