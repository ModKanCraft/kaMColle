package im.kaMColle;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.Vec3;

public enum KansouAttchments {
	Test("",Vec3.createVectorHelper(0d,0d,0d)),
	BBTurret("",
			Vec3.createVectorHelper(0d,0d,0d),
			Vec3.createVectorHelper(0d,0d,0d),
			Vec3.createVectorHelper(0d,0d,0d),
			Vec3.createVectorHelper(0d,0d,0d)
			//四个炮塔的位置
	), 
	//只用设置一条腿的偏移，以右腿为参照做右腿的，左腿对称过去
	TorpedoLauncher("leg",Vec3.createVectorHelper(0d,0d,0d)),
	//飞行甲板只有一个
	BBVLaunchPad("right arm",Vec3.createVectorHelper(0d,0d,0d)),
	CVLaunchPad(""),//这个没想好
	CLTurret("right arm",Vec3.createVectorHelper(0d,0d,0d),Vec3.createVectorHelper(0d,0d,0d)),
	DDTurret("right arm",Vec3.createVectorHelper(0d,0d,0d)),
	CATurret("right arm",Vec3.createVectorHelper(0d,0d,0d),Vec3.createVectorHelper(0d,0d,0d));
	public String part;
	public Vec3[] offsets;
	private KansouAttchments(String part,Vec3... offsets){
		this.part=part;
		this.offsets=offsets;
	}
	public ArrayList<ModelRenderer> getPart(ModelBiped model){
		String string=this.part;
		ArrayList<ModelRenderer> r = new ArrayList<ModelRenderer>(1);
		switch(string){
		case "leg":
			r.add(model.bipedLeftLeg);
			r.add(model.bipedRightLeg);
		case "right arm":
			r.add(model.bipedRightArm);
		default:
			r.add(model.bipedBody);
		}
		return r;
	}
}
