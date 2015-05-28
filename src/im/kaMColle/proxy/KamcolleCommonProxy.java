package im.kaMColle.proxy;

import java.util.HashSet;
import java.util.Set;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class KamcolleCommonProxy {

	public void preInit(){}
	    
	public static Set<String> languages = new HashSet(); 
	  
 	static { 
 		languages.add("zh_CN"); 
 		languages.add("en_US"); 
 	} 
 	 
 	public void init() { 
 		for (String lang : languages) { 
 			LanguageRegistry.instance().loadLocalization( 
 					"/assets/gimmickery/lang/" + lang + ".properties", lang, false); 
 		} 
 	}

	public void postInit(){}

}