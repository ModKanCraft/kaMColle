package im.kaMColle.render;

import im.kaMColle.FleetClass;
import im.kaMColle.OBJmodels.KamcolleOBJModelResourceManager;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.init.Items;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RenderPlayerKansou {
	ModelBoat model=new ModelBoat();
	KamcolleOBJModelResourceManager modelManager=KamcolleOBJModelResourceManager.getManager();
	/*这里是笔记：
	 *[C]LI-狼:
	 *所以如果你要渲染世界里其他玩家的效果，还得自己处理下变换，不过也不难
	 *GL11.glTranslated(
	 *otherPlayer.posX - RenderManager.renderPosX, 
	 *otherPlayer.posY - RenderManager.renderPosY, 
	 *otherPlayer.posZ - RenderManager.renderPosZ);
	 */
	 /*
	  * 好吧其实我没能自己实现这玩意
	  * 但是Test2就不用我自己实现啊，果断以后用Test2
	  */
	@SubscribeEvent
	public void renderPlayerKansouTest(RenderPlayerEvent.Specials.Post event){
		//测试3
		if(event.entityPlayer.getEntityData().getString("FleetClass").equals("TEST")){
			GL11.glPushMatrix();
			GL11.glScalef(1/16F, 1/16F, 1/16F);
			GL11.glTranslatef(
					0, 
					event.renderer.modelBipedMain.bipedLeftLeg.rotationPointY, 
					event.renderer.modelBipedMain.bipedLeftLeg.rotationPointZ+8
					);
			GL11.glRotatef(event.renderer.modelBipedMain.bipedLeftLeg.rotateAngleX*(180F/(float)Math.PI), 1, 0, 0);
			GL11.glTranslatef(
					0, 
					event.renderer.modelBipedMain.bipedLeftLeg.offsetY, 
					event.renderer.modelBipedMain.bipedLeftLeg.offsetZ
					);
			GL11.glScalef(1/2F, 1/2F, 1/2F);
			GL11.glRotatef(180, 1, 0, 0);
			modelManager.renderKansouModel(FleetClass.TEST);
			GL11.glPopMatrix();
		}
	}
}
