package im.kaMColle.tileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SallyBoardTileEntity extends TileEntity {
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
