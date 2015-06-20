package im.kaMColle.OBJmodels;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.client.model.obj.*;
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
	private TextureManager textureManager;//RenderManager.instance.renderEngine;
	
	private ResourceLocation modelTestTexture;
	
	private KamcolleOBJModelResourceManager(){
		textureManager=Minecraft.getMinecraft().renderEngine;
		modelTest=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/Test.obj"));
		//modelTorpedoLauncher=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/TorpedoLauncher.obj"));
		//modelBBTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/BBTurret.obj"));
		//modelCATurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/CATurret.obj"));
		//modelCLTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/CLTurret.obj"));
		modelTestTexture=new ResourceLocation(Kamcolle.ID,"textures/models/Test.jpg");
	}
	
	public static KamcolleOBJModelResourceManager getManager(){
		if(instance==null){
			instance=new KamcolleOBJModelResourceManager();
		}
		return instance;
	}
	public void renderKansouModel(FleetClass Class){
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
	}
	public void renderModelSallyBoard(){
		
	}
}
