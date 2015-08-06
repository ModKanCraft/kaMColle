package im.kaMColle.render;

import im.kaMColle.FleetClass;
import im.kaMColle.KansouAttchments;
import im.kaMColle.ModelAttachPoint;
import im.kaMColle.OBJmodels.KamcolleOBJModelResourceManager;
import im.kaMColle.hackModels.DDPose;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegEventHandler;
import cn.annoreg.mc.RegEventHandler.Bus;
import cn.liutils.util.generic.RegistryUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
@Registrant
@RegEventHandler(Bus.Forge)
public class RenderPlayerKansou {
	KamcolleOBJModelResourceManager modelManager=KamcolleOBJModelResourceManager.getManager();
	RenderManager renderManager=RenderManager.instance;
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
	ModelBiped hackMain, hackArmor, hackChestplate, hackModel;
	ModelBiped realMain, realArmor, realChestplate, realModel;
	private static HashMap<FleetClass,ModelBiped[]> poseMap=new HashMap();
	
	private static Field fldmain;
	static {
		try {
			fldmain = RegistryUtils.getObfField(RendererLivingEntity.class, "mainModel", "field_77045_g");
			fldmain.setAccessible(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		registPose(FleetClass.NULL,ModelBiped.class);
	} 
	public RenderPlayerKansou() {
		// TODO Auto-generated constructor stub
	}
	
	@SubscribeEvent
	public void renderPlayerKansouOnThirdView(RenderPlayerEvent.Specials.Post event){
		NBTTagCompound data=event.entityPlayer.getEntityData();
		String KansouType=event.entityPlayer.getEntityData().getString("FleetClass");
		if(KansouType.isEmpty())return;
		FleetClass Class=FleetClass.valueOf(KansouType);
		ModelBiped model=event.renderer.modelBipedMain;
		renderKansouOnModel(Class, model);
	}
	@SubscribeEvent
	public void startRender(RenderPlayerEvent.Pre event){
		NBTTagCompound data=event.entity.getEntityData();
		RenderPlayer render = event.renderer;
		//Init last models
		ModelBiped[] pose=getModel(event.entity);
		hackMain = hackModel = pose[0];
		hackChestplate = pose[2];
		hackArmor =  pose[1];
		if(this.realMain==null){
			realMain = render.modelBipedMain;
			realArmor = render.modelArmor;
			realChestplate = render.modelArmorChestplate;
			realModel = getMainModel(render);
		}
		
		//Replace
		if(data.getBoolean("isInWater")) {
			render.modelBipedMain = hackMain;
			render.modelArmor = hackArmor;
			render.modelArmorChestplate = hackChestplate;
			setMainModel(render, hackModel);
		}
	}
	@SubscribeEvent
	public void endRender(RenderPlayerEvent.Post event) {
		//Restore last models
		RenderPlayer render = event.renderer;
		render.modelBipedMain = realMain;
		render.modelArmor = realArmor;
		render.modelArmorChestplate = realChestplate;
		setMainModel(render, realModel);
	}
	public void renderKansouOnModel(FleetClass Class,ModelBiped model){
		for(KansouAttchments att:Class.Kansou){
			if(att.getPart(model)!=null){
				for(ModelRenderer r:att.getPart(model)){
					for(ModelAttachPoint point:att.offsets){
						GL11.glPushMatrix();
						GL11.glScalef(1/16F, 1/16F, 1/16F);//这个比率不用调
						GL11.glTranslatef(
								r.rotationPointX, 
								r.rotationPointY, 
								r.rotationPointZ
								);
						if (r.rotateAngleZ != 0.0F){
							GL11.glRotatef(r.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
						}
						if (r.rotateAngleY != 0.0F){
							GL11.glRotatef(r.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
						}
						if (r.rotateAngleX != 0.0F) {
							GL11.glRotatef(r.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
						}
						double scale=att.scale;
						if(r.mirror)GL11.glScalef(-1F, 1F, 1F);
						GL11.glTranslated(
								-point.x, 
								-point.y, 
								-point.z
								);
						GL11.glScaled(scale,scale,scale);
						double degree=Math.acos(point.direction.yCoord)*180d/Math.PI;
						GL11.glRotated(degree, -point.direction.zCoord, 0, point.direction.xCoord);
						modelManager.renderKansouModel(att);
						GL11.glPopMatrix();
					}
				}
			}
		}
	}
	private void setMainModel(RenderPlayer r, ModelBiped m) {
		try {
			fldmain.set(r, m);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private ModelBiped getMainModel(RenderPlayer r) {
		try {
			return (ModelBiped) fldmain.get(r);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	public static void registPose(FleetClass fc,Class<? extends ModelBiped> clazz){
		ModelBiped[] pose=new ModelBiped[3];
		pose[0]=invokePoseModel(0F,clazz);
		pose[1]=invokePoseModel(0.5F,clazz);
		pose[2]=invokePoseModel(1F,clazz);
		poseMap.put(fc,pose);
	}
	private static ModelBiped invokePoseModel(float scale,Class clazz){
		try {
			return (ModelBiped) clazz.getDeclaredConstructor(float.class).newInstance(scale);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return invokePoseModel(scale,ModelBiped.class);
		}
	}
	public static ModelBiped[] getModel(FleetClass fc){
		ModelBiped[] pose=poseMap.get(fc);
		return pose;
	}
	public static ModelBiped[] getModel(Entity e){
		FleetClass fc=FleetClass.valueOf(e.getEntityData().getString("FleetClass"));
		if(!poseMap.containsKey(fc))fc=FleetClass.NULL;
		return getModel(fc);
	}
}
