package im.kaMColle.OBJmodels;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import im.kaMColle.KansouAttchments;
import im.kaMColle.block.FleetSallyBoard;
import im.kaMColle.tileEntity.SallyBoardTileEntity;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class KamcolleOBJModelResourceManager {
	private static KamcolleOBJModelResourceManager instance;

	HashMap<KansouAttchments,Models> modelsMap=new HashMap();
	HashMap<Class<? extends Block>,Models> blockModelsMap=new HashMap();
	
	private TextureManager textureManager;//RenderManager.instance.renderEngine;
	
	private ResourceLocation modelTestTexture;
	private ResourceLocation modelBBTurretTexture;
	private ResourceLocation modelDDBridgeTexture;
	
	private IModelCustom modelTorpedoLauncher;
	private IModelCustom modelTest;
	private IModelCustom modelBBBridge;
	private IModelCustom modelBBTurret;
	private IModelCustom modelSSVLauncher;
	private IModelCustom modelDDBridge;
	private IModelCustom modelDDTurret;

	private IModelCustom modelSallyBoard;



	
	private KamcolleOBJModelResourceManager(){
		textureManager=Minecraft.getMinecraft().renderEngine;
		modelTest=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/Test.obj"));
		modelTorpedoLauncher=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/DD/launcher.obj"));
		modelBBTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/BB/BB_Turret.obj"));
		modelBBBridge=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/BB/BB_Back.obj"));
		modelDDBridge=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/DD/DD_Back.obj"));
		modelDDTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/DD/DD_Turret.obj"));
		//modelCATurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/CATurret.obj"));
		//modelCLTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/CLTurret.obj"));
		//modelDDTurret=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/DDTurret.obj"));
		modelSSVLauncher=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/SS/SSV_1.obj"));
		
		modelSallyBoard=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/start_block/start_block.obj"));
		//...
		modelTestTexture=new ResourceLocation(Kamcolle.ID,"textures/checkerboard.png");
		modelBBTurretTexture=new ResourceLocation(Kamcolle.ID,"textures/models/BBturret.png");
		modelDDBridgeTexture=new ResourceLocation(Kamcolle.ID,"textures/models/fubuki_back.png");
		//...
		modelsMap.put(KansouAttchments.Test, new Models(modelTest));
		modelsMap.put(KansouAttchments.TorpedoLauncher, new Models(modelTorpedoLauncher,modelTestTexture));
		modelsMap.put(KansouAttchments.BBBridge, new Models(modelBBBridge));
		modelsMap.put(KansouAttchments.BBTurret, new Models(modelBBTurret,modelBBTurretTexture));
		modelsMap.put(KansouAttchments.SSVLauncher, new Models(modelSSVLauncher,modelTestTexture));
		modelsMap.put(KansouAttchments.DDBridge, new Models(modelDDBridge,modelDDBridgeTexture));
		modelsMap.put(KansouAttchments.DDTurret, new Models(modelDDTurret));
		
		//...
		blockModelsMap.put(FleetSallyBoard.class, new Models(modelSallyBoard,this.modelTestTexture));
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
	public void renderInventoryBlockModel(Block block){
		Models models=this.blockModelsMap.get(block.getClass());
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		if(models.hasTexture){
			textureManager.bindTexture(models.texture);
		}else{
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glColor3f(30F, 30F, 30F);
		}
		GL11.glScalef(-0.1F,-0.1F,-0.1F);
		models.model.renderAll();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
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
		GL11.glScalef(-1F, -1F, -1F);
		models.model.renderAll();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	public void renderModelSallyBoard(SallyBoardTileEntity t){
		GL11.glPushMatrix();
		textureManager.bindTexture(this.modelTestTexture);
		GL11.glScaled(1/18D, 1/18D, 1/18D);
		GL11.glRotatef(90, -1F, 0F, 0F);
		modelSallyBoard.renderAllExcept("button");;
		if(t.isOccupied)GL11.glTranslatef(0,0,-1.8F);
		modelSallyBoard.renderPart("button");
		GL11.glPopMatrix();
	}
	private Models getModel(KansouAttchments type){
		if(modelsMap.containsKey(type))
			return modelsMap.get(type);
		return modelsMap.get(KansouAttchments.Test);
	}
}
