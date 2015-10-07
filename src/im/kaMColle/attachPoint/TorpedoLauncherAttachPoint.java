package im.kaMColle.attachPoint;

import org.lwjgl.opengl.GL11;

import im.kaMColle.ModelAttachPoint;
import net.minecraft.entity.Entity;

public class TorpedoLauncherAttachPoint extends ModelAttachPoint {
	private TorpedoLauncherAttachPoint(double x,double y,double z){
		super(x,y,z);
	}
	
	@Override
	public void setToRotationByEntityData(Entity e){
		boolean isAimming=e.getDataWatcher().getWatchableObjectByte(26)==1;
		GL11.glRotatef(90, -1, 0, 0);
	}
}
