package im.kaMColle.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import im.kaMColle.OBJmodels.KamcolleOBJModelResourceManager;

public class OBJBlockRenderer implements ISimpleBlockRenderingHandler {
	public OBJBlockRenderer(){
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		KamcolleOBJModelResourceManager.getManager().renderInventoryBlockModel(block);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return 3939;
	}

}
