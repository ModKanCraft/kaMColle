package im.kaMColle.OBJmodels;

import org.lwjgl.opengl.GL11;

import im.kaMColle.Kamcolle;
import im.kaMColle.FleetClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class KamcolleOBJModelResourceManager {
	private static KamcolleOBJModelResourceManager instance;
	private static IModelCustom modelTest;
	private TextureManager textureManager=RenderManager.instance.renderEngine;
	
	private ResourceLocation modelTestTexture=new ResourceLocation(Kamcolle.ID,"models/Test.jpg");
	
	private KamcolleOBJModelResourceManager(){
		modelTest=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/Test.obj"));
	}
	
	public static KamcolleOBJModelResourceManager getManager(){
		if(instance==null){
			instance=new KamcolleOBJModelResourceManager();
		}
		return instance;
	}
	public void RederModel(FleetClass Class){
		GL11.glPushMatrix();
		switch(Class){
		case TEST:
			textureManager.bindTexture(modelTestTexture);
			modelTest.renderAll();
			break;
		default:
			break;
		}
		GL11.glPopMatrix();
	}
}
