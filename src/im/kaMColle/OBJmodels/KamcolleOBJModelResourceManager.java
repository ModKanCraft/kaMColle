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
	private IModelCustom modelTest;

	HashMap<KansouAttchments,Models> modelsMap=new HashMap();
	private TextureManager textureManager;//RenderManager.instance.renderEngine;
	
	private ResourceLocation modelTestTexture;
	
	private KamcolleOBJModelResourceManager(){
		textureManager=Minecraft.getMinecraft().renderEngine;
		modelTest=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/Test.obj"));
		//modelTorpedoLauncher=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/TorpedoLauncher.obj"));
		//modelBBTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/BBTurret.obj"));
		//modelCATurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/CATurret.obj"));
		//modelCLTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/CLTurret.obj"));
		//modelDDTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/DDTurret.obj"));
		//...
		modelTestTexture=new ResourceLocation(Kamcolle.ID,"textures/models/Test.jpg");
		//...
		modelsMap.put(KansouAttchments.Test, new Models(modelTest,modelTestTexture));
		//...
		//弄好一个加一个
	}
	private class Models{
		IModelCustom model;
		ResourceLocation texture;
		Models(IModelCustom model,ResourceLocation texture){
			this.model=model;
			this.texture=texture;
		}
	}
	
	public static KamcolleOBJModelResourceManager getManager(){
		if(instance==null){
			instance=new KamcolleOBJModelResourceManager();
		}
		return instance;
	}
	public void renderKansouModel(FleetClass Class){
		if(Class.Kansou==null)return;
		for(KansouAttchments string:Class.Kansou){
			Models models=getModel(string);
			textureManager.bindTexture(models.texture);
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_CULL_FACE);
			models.model.renderAll();
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glPopMatrix();
		}
		/*
	GL11.glPushMatrix();
		switch(Class){
		case TEST:
			//textureManager.bindTexture(modelTestTexture);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glColor3f(30F, 30F, 30F);
			ArrayList<String> names = new ArrayList();
			for(GroupObject object:((WavefrontObject) modelTest).groupObjects){
				names.add(object.name);
			}
			for(String name:names){
				modelTest.renderPart(name);
			}
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			break;
		default:
			break;
		}
		GL11.glPopMatrix();
*/
	}
	public void renderModelSallyBoard(){
		
	}
	private Models getModel(KansouAttchments type){
		if(modelsMap.containsKey(type))return modelsMap.get(type);
		Kamcolle.LogInfo("No Such A Model,Return Model Test!");
		return modelsMap.get(KansouAttchments.Test);
	}
}
