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
	
	public PoseModel(float f) {
		// TODO Auto-generated constructor stub
		super(f);
	}

	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_,
			float p_78087_3_, float headAngleY, float headAngleX,
			float p_78087_6_, Entity p_78087_7_) {
		// TODO Auto-generated method stub
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, headAngleY,
				headAngleX, p_78087_6_, p_78087_7_);
		EntityPlayer player=(EntityPlayer) p_78087_7_;
		this.bipedLeftLeg.rotateAngleX=0;
		this.bipedRightLeg.rotateAngleX=0;
		this.bipedBody.isHidden=true;
		/*
		FleetClass fleetClass=FleetClass.valueOf(player.getEntityData().getString("FleetClass"));
		ScriptProgram pose=poseMap.get(fleetClass);
		ScriptFunction function=pose.root.getFunction("setPartsAngles");
		function.callObject(this,p_78087_1_,p_78087_2_);
		*/
	}
}
