package im.kaMColle.render;

import im.kaMColle.FleetClass;
import im.kaMColle.KansouAttchments;
import im.kaMColle.OBJmodels.KamcolleOBJModelResourceManager;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RenderPlayerKansou {
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
	public void renderPlayerKansouOnThirdView(RenderPlayerEvent.Specials.Post event){
		String KansouType=event.entityPlayer.getEntityData().getString("FleetClass");
		if(KansouType.isEmpty())return;
		FleetClass Class=FleetClass.valueOf(KansouType);
		ModelBiped model=event.renderer.modelBipedMain;
		renderKansouOnModel(Class, model);
	}
	public void renderPlayerKansouOnFirstView(){
		
	}
	public void renderKansouOnModel(FleetClass Class,ModelBiped model){
		for(KansouAttchments att:Class.Kansou){
			if(att.getPart(model)!=null){
				for(ModelRenderer r:att.getPart(model)){
					for(KansouAttchments.Point point:att.offsets){
						GL11.glPushMatrix();
						GL11.glScalef(1/16F, 1/16F, 1/16F);//这个比率不用调
						GL11.glTranslatef(
								r.rotationPointX, 
								r.rotationPointY, 
								r.rotationPointZ
								);
						GL11.glRotatef(r.rotateAngleX*(180F/(float)Math.PI), 1, 0, 0);
						GL11.glRotatef(r.rotateAngleY*(180F/(float)Math.PI), 0, 1, 0);
						GL11.glRotatef(r.rotateAngleZ*(180F/(float)Math.PI), 0, 0, 1);
						if(r.mirror)GL11.glScalef(-1F, 1F, 1F);
						GL11.glTranslated(
								-point.x, 
								-point.y, 
								-point.z
								);
						double scale=att.scale;
						GL11.glScaled(scale,scale,scale);
						modelManager.renderKansouModel(att);
						GL11.glPopMatrix();
					}
				}
			}
		}
	}
}
