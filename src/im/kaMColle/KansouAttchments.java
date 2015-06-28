package im.kaMColle;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.Vec3;

public enum KansouAttchments {
	
	/**
	 * 第一个参数字符串是附着的部位，新增的话在getPart方法里面照葫芦画瓢增加case就是了
	 * 要是直接绑在玩家的身体上（就是Body），那么第一个字符串可以就填一个""或者其他任何【那几个CASE的字符串】
	 * 后面的Vec3是用来表示偏移位置的
	 * 每个Vec3代表一个附着位置
	 * 有多个Vec3就会在这多个Vec3上都进行渲染
	 */
	Test("",0.1d,Point.get(0d,0d,0d)),
	BBTurret("",0.05d,
			Point.get(20d,-10d,-10d),
			Point.get(10d,-5d,-10d),
			Point.get(-10d,-5d,-10d),
			Point.get(-20d,-10d,-10d)
			//四个炮塔的位置
	), 
	//只用设置一条腿的偏移，以右腿为参照做右腿的，左腿会自行对称过去
	TorpedoLauncher("leg",0.1d,Point.get(0d,0d,0d)),
	//飞行甲板只有一个
	BBVLaunchPad("right arm",0.1d,Point.get(0d,0d,0d)),
	CVLaunchPad("",0.1d),//这个没想好
	CLTurret("right arm",0.1d,Point.get(0d,0d,0d),Point.get(0d,0d,0d)),
	DDTurret("right arm",0.1d,Point.get(0d,0d,0d)),
	CATurret("right arm",0.1d,Point.get(0d,0d,0d),Point.get(0d,0d,0d));
	public String part;
	public double scale;
	public Point[] offsets;
	private KansouAttchments(String part,double scale,Point... offsets){
		this.part=part;
		this.scale=scale;
		this.offsets=offsets;
	}
	public static class Point{
		public double x;
		public double y;
		public double z;
		private Point(double x,double y,double z){
			this.x=x;
			this.y=y;
			this.z=z;
		}
		public static Point get(double x,double y,double z){
			return new Point(x,y,z);
		}
	}
	public ArrayList<ModelRenderer> getPart(ModelBiped model){
		String string=this.part;
		ArrayList<ModelRenderer> r = new ArrayList<ModelRenderer>(1);
		switch(string){
		case "leg":
			r.add(model.bipedLeftLeg);
			//r.add(model.bipedHead)
			//我们不用武装到牙齿就能成为重雷装巡洋舰(什么鬼
			r.add(model.bipedRightLeg);
			break;
		case "right arm":
			r.add(model.bipedRightArm);
			break;
		default:
			r.add(model.bipedBody);
			break;
		}
		return r;
	}
}
