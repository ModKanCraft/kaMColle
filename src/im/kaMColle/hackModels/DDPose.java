package im.kaMColle.hackModels;

import cn.annoreg.core.Registrant;
import im.kaMColle.FleetClass;
import im.kaMColle.anno.RegPose;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
@Registrant
@RegPose(FleetClass.DD)
public class DDPose extends ModelBiped {
	public DDPose(float f) {
		super(f);
		// TODO Auto-generated constructor stub
	}
	public void setRotationAngles(float walk_time, float max_swing, float time,
			float headangleY, float headangleX, float scale, Entity entity){
		super.setRotationAngles(walk_time,max_swing,time,headangleY,headangleX,scale,entity);
		this.bipedLeftLeg.rotateAngleX=0;
		this.bipedRightLeg.rotateAngleX=0;
		this.bipedRightArm.rotateAngleX=this.bipedHead.rotateAngleX-(float) (Math.PI/2);
		this.bipedRightArm.rotateAngleY=this.bipedHead.rotateAngleY;
		this.bipedLeftArm.rotateAngleX=this.bipedRightArm.rotateAngleX+(float) (Math.PI/16);
		this.bipedLeftArm.rotateAngleY=Math.min(this.bipedRightArm.rotateAngleY+(float) (Math.PI/4), (float) (Math.PI/8*3));
	}
}
