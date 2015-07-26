package im.kaMColle.hackModels;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;

public class PoseModel extends ModelBiped {
	public PoseModel(float f) {
		// TODO Auto-generated constructor stub
		super(f);
	}

	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_,
			float p_78087_3_, float p_78087_4_, float p_78087_5_,
			float p_78087_6_, Entity p_78087_7_) {
		// TODO Auto-generated method stub
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_,
				p_78087_5_, p_78087_6_, p_78087_7_);
		this.bipedLeftLeg.rotateAngleX=0;
		this.bipedRightLeg.rotateAngleX=0;
	}
	public void setPose(){
		
	}
}
