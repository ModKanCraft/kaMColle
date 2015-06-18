package im.kaMColle.render.tileEntity;

import im.kaMColle.OBJmodels.KamcolleOBJModelResourceManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class SallyBoardSpecialRenderer extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x,
			double y, double z, float Scale) {
		// TODO Auto-generated method stub
		GL11.glPushMatrix();
		GL11.glTranslated(x,y,z);
		KamcolleOBJModelResourceManager.getManager().renderModelSallyBoard();
		GL11.glPopMatrix();
	}
}
