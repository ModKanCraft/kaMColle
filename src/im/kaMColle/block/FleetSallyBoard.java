package im.kaMColle.block;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import im.kaMColle.network.KamcolleKansouChange;
import im.kaMColle.network.MessageHandler;
import im.kaMColle.tileEntity.SallyBoardTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FleetSallyBoard extends BlockContainer {

	public FleetSallyBoard() {
		super(Material.rock);
		this.setBlockName("FleetSallyBoard");
		this.setCreativeTab(Kamcolle.tab);
		this.setBlockBounds(0F, 0F, 0F, 1F, 0.65F, 1F);
		this.setLightOpacity(0);
		this.setHardness(0.8F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new SallyBoardTileEntity();
	}
	
	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param entity
	 * 当实体走过方块一次
	 * 以后在这里加GUI
	 */
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity){
		Kamcolle.LogInfo(entity);
		if(entity instanceof EntityPlayer&&entity.worldObj.isRemote){
			EntityPlayer player=(EntityPlayer) entity;
			if(!(player.getEntityData().getString("FleetClass").isEmpty()||player.getEntityData().getString("FleetClass").equals("NULL"))){
				KamcolleKansouChange a=KamcolleKansouChange.getKansouChangePacket(player, FleetClass.NULL);
				MessageHandler.INSTANCE.sendToServer(a);
			}else{;
				KamcolleKansouChange a=KamcolleKansouChange.getKansouChangePacket(player, FleetClass.CL);
				MessageHandler.INSTANCE.sendToServer(a);
			}
		}
	}
	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
		effectRenderer.clearEffects(worldObj);
		//在这里加上新的方块被挖掘的特效
		
        return true;
    }
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
		effectRenderer.clearEffects(world);
		//在这里加上新的方块破坏特效
		
        return true;
    }
	
	public boolean isOpaqueCube(){
		return false;
	}
	public int getRenderType(){
		return 3939;
	}
	public boolean renderAsNormalBlock(){
        return false;
    }
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face){
        return false;
    }
}
