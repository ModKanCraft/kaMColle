package im.kaMColle;

import net.minecraft.util.Vec3;

public class ModelAttachPoint {
	public double x;
	public double y;
	public double z;
	public Vec3 direction=Vec3.createVectorHelper(0, 1, 0);
	private ModelAttachPoint(double x,double y,double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public static ModelAttachPoint get(double x,double y,double z){
		return new ModelAttachPoint(x,y,z);
	}
	public ModelAttachPoint setDirectionVec3(double x1,double y1,double z1){
		return setDirectionVec3(Vec3.createVectorHelper(x1, y1, z1));
	}
	public ModelAttachPoint setDirectionVec3(Vec3 vec) {
		// TODO Auto-generated method stub
		this.direction=vec.normalize();
		return this;
	}
}
