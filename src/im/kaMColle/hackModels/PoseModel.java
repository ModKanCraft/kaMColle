package im.kaMColle.hackModels;

import java.util.HashMap;

import cn.liutils.ripple.ScriptFunction;
import cn.liutils.ripple.ScriptProgram;
import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class PoseModel extends ModelBiped {
	//public static HashMap<FleetClass,V> poseMap=new HashMap();
	/*static{
		for(FleetClass fc:FleetClass.values()){
			
			poseMap.put(fc, pose);
		}
	}
	*/
	float f1=0;
	public PoseModel(float f) {
		// TODO Auto-generated constructor stub
		super(f);
	}


	public void setRotationAngles(float walking_time_delta, float p_78087_2_,
			float delta, float headAngleY, float headAngleX,
			float scale, Entity p_78087_7_) {
		// TODO Auto-generated method stub
		super.setRotationAngles(walking_time_delta, p_78087_2_, delta, headAngleY,
				headAngleX, scale, p_78087_7_);
		EntityPlayer player=(EntityPlayer) p_78087_7_;
		this.bipedLeftLeg.rotateAngleX=0;
		this.bipedRightLeg.rotateAngleX=0;
		this.bipedRightArm.rotateAngleX=this.bipedHead.rotateAngleX-(float) (Math.PI/2);
		this.bipedRightArm.rotateAngleY=this.bipedHead.rotateAngleY;
		this.bipedLeftArm.rotateAngleX=this.bipedRightArm.rotateAngleX+(float) (Math.PI/16);
		this.bipedLeftArm.rotateAngleY=Math.min(this.bipedRightArm.rotateAngleY+(float) (Math.PI/4), (float) (Math.PI/8*3));
	}
}
