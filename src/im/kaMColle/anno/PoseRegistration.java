package im.kaMColle.anno;

import cn.annoreg.base.RegistrationClassSimple;
import cn.annoreg.core.LoadStage;
import cn.annoreg.core.RegistryTypeDecl;
import im.kaMColle.render.RenderPlayerKansou;
import net.minecraft.client.model.ModelBiped;
@RegistryTypeDecl
public class PoseRegistration extends RegistrationClassSimple<RegPose, ModelBiped> {

	public PoseRegistration() {
		super(RegPose.class, "SubmodulePoseData");
		this.setLoadStage(LoadStage.INIT);
	}

	@Override
	protected void register(Class<? extends ModelBiped> theClass, RegPose anno) throws Exception {
		RenderPlayerKansou.registPose(anno.value(), theClass);
	}

}
