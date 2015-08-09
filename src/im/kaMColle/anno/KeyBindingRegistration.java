package im.kaMColle.anno;

import java.lang.annotation.Annotation;

import cn.annoreg.base.RegistrationFieldSimple;
import cn.annoreg.core.LoadStage;
import cn.annoreg.core.RegistryTypeDecl;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import im.kaMColle.Kamcolle;
import net.minecraft.client.settings.KeyBinding;
@SideOnly(Side.CLIENT)
@RegistryTypeDecl
public class KeyBindingRegistration extends RegistrationFieldSimple {

	public KeyBindingRegistration() {
		super(RegKeyBingding.class, "SubmoduleKeyBinding");
		this.setLoadStage(LoadStage.INIT);
	}

	@Override
	protected void register(Object value, Annotation anno, String field) throws Exception {
		// TODO Auto-generated method stub
		try{
			ClientRegistry.registerKeyBinding((KeyBinding) value);
		}catch(ClassCastException e){
			Kamcolle.LOGGER.error("Trying regist an object which named "+field+" for KeyBingding,yet it can't case into it._(:3」∠)_");
		}
	}


}
