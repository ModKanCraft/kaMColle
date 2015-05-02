package im.kaMColle.OBJmodels;

import im.kaMColle.Kamcolle;
import im.kaMColle.ShipClass;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class KamcolleOBJModelResourceManager {
	private static KamcolleOBJModelResourceManager instance;
	private static IModelCustom modelTest;
	private KamcolleOBJModelResourceManager(){
		modelTest=AdvancedModelLoader.loadModel(new ResourceLocation(Kamcolle.ID,"models/Test.obj"));
	}
	public static KamcolleOBJModelResourceManager getManager(){
		if(instance==null){
			instance=new KamcolleOBJModelResourceManager();
		}
		return instance;
	}
	public void RederModel(String string){
		switch(ShipClass.toClassType(string)){
		case TEST:
			modelTest.renderAll();
		default:
			break;
		}
	}
}
