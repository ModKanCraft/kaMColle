package im.kaMColle.bolck;

import im.kaMColle.Kamcolle;
import im.kaMColle.tileEntity.SallyBoardTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class FleetSallyBoard extends BlockContainer {

	public FleetSallyBoard() {
		super(Material.rock);
		this.setCreativeTab(Kamcolle.tab);
		this.setBlockBounds(0F, 0F, 0F, 1F, 0.9F, 1F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new SallyBoardTileEntity();
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity){
		if(entity instanceof EntityPlayer){
			EntityPlayer player=(EntityPlayer) entity;
			if(player.getEntityData().getString("FleetClass").equals("TEST")){
				player.getEntityData().removeTag("FleetClass");
			}else{
				player.getEntityData().setString("FleetClass", "TEST");
			}
		}
	}
	
	@Override
	public boolean isOpaqueCube(){
		return true;
	}

}
