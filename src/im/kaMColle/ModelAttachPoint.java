package im.kaMColle;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class ModelAttachPoint {
	public double x;
	public double y;
	public double z;
	public Vec3 direction=Vec3.createVectorHelper(0, 1, 0);
	protected ModelAttachPoint(double x,double y,double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	
	public ModelAttachPoint setDirectionVec3(double x1,double y1,double z1){
		return setDirectionVec3(Vec3.createVectorHelper(x1, y1, z1));
	}
	
	public ModelAttachPoint setDirectionVec3(Vec3 vec) {
		// TODO Auto-generated method stub
		this.direction=vec.normalize();
		return this;
	}
	
	public void setToRotationByEntityData(Entity entity){
		double degree=Math.acos(this.direction.yCoord)*180d/Math.PI;
		GL11.glRotated(degree, -this.direction.zCoord, 0, this.direction.xCoord);
	}
}
