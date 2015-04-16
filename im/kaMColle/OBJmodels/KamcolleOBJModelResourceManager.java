package im.kaMColle.OBJmodels;

public class KamcolleOBJModelResourceManager {
	private static KamcolleOBJModelResourceManager instance;
	private KamcolleOBJModelResourceManager(){
		//getModelResource
		/*
		 * if(isZip){
		 * getResourceFromZip();
		 * }else{
		 * getResourceFromFolder();
		 * }
		 */
	}
	private static KamcolleOBJModelResourceManager getManager(){
		if(instance==null){
			instance=new KamcolleOBJModelResourceManager();
		}
		return instance;
	}
}
