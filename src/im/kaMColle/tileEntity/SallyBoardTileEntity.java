package im.kaMColle.tileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import im.kaMColle.render.tileEntity.SallyBoardSpecialRenderer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegTileEntity;
@Registrant
@RegTileEntity
@RegTileEntity.HasRender
public class SallyBoardTileEntity extends TileEntity {
	@SideOnly(Side.CLIENT)
	@RegTileEntity.Render
	public static SallyBoardSpecialRenderer renderer;
	public boolean isOccupied=false;
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		nbt.setBoolean("isOccupied", this.isOccupied);
		super.writeToNBT(nbt);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		this.isOccupied=nbt.getBoolean("isOccupied");
	}
}
