package im.kaMColle.OBJmodels;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import im.kaMColle.KansouAttchments;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class KamcolleOBJModelResourceManager {
	private static KamcolleOBJModelResourceManager instance;

	HashMap<KansouAttchments,Models> modelsMap=new HashMap();
	private TextureManager textureManager;//RenderManager.instance.renderEngine;
	
	private ResourceLocation modelTestTexture;
	private ResourceLocation modelBBTurretTexture;
	
	private IModelCustom modelTorpedoLauncher;
	private IModelCustom modelTest;
	private IModelCustom modelBBTurret;

	
	private KamcolleOBJModelResourceManager(){
		textureManager=Minecraft.getMinecraft().renderEngine;
		modelTest=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/Test.obj"));
		modelTorpedoLauncher=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/torpedo/launcher.obj"));
		modelBBTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/turret/BBTurret.obj"));
		//modelCATurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/CATurret.obj"));
		//modelCLTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/CLTurret.obj"));
		//modelDDTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/DDTurret.obj"));
		//...
		modelTestTexture=new ResourceLocation(Kamcolle.ID,"textures/checkboard.tga");
		modelBBTurretTexture=new ResourceLocation(Kamcolle.ID,"textures/models/BBturret.png");
		//...
		modelsMap.put(KansouAttchments.Test, new Models(modelTest));
		modelsMap.put(KansouAttchments.TorpedoLauncher, new Models(modelTorpedoLauncher));
		modelsMap.put(KansouAttchments.BBTurret, new Models(modelBBTurret,modelBBTurretTexture));
		//...
		//弄好一个加一个
	}
	private class Models{
		IModelCustom model=null;
		ResourceLocation texture=null;
		boolean hasTexture=false;
		Models(IModelCustom model,ResourceLocation texture){
			this.model=model;
			this.texture=texture;
			if(this.texture!=null)this.hasTexture=true;
		}
		Models(IModelCustom model){
			this.model=model;
		}
	}
	
	public static KamcolleOBJModelResourceManager getManager(){
		if(instance==null){
			instance=new KamcolleOBJModelResourceManager();
		}
		return instance;
	}
	public void renderKansouModel(KansouAttchments att){
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		Models models=getModel(att);
		if(models.hasTexture){
			textureManager.bindTexture(models.texture);
		}else{
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glColor3f(30F, 30F, 30F);
		}
		GL11.glScalef(-1F, 1F, 1F);
		GL11.glRotatef(90F, 1, 0, 0);
		models.model.renderAll();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	public void renderModelSallyBoard(){
		
	}
	private Models getModel(KansouAttchments type){
		if(modelsMap.containsKey(type))
			return modelsMap.get(type);
		Kamcolle.LogInfo("No Such A Model For Attchment:"+type.name()+",Return Model Test!");
		return modelsMap.get(KansouAttchments.Test);
	}
}
