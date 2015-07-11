package im.kaMColle.item;

import java.util.List;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class KamcolleFleetCard extends Item {
	public KamcolleFleetCard(){
		this.setNoRepair();
		this.setCreativeTab(Kamcolle.TAB);
		this.hasSubtypes=true;
	}
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer entityPlayer){
		itemStack.stackTagCompound.setString("FleetClass", FleetClass.getRandomClass().name());
	}
	public void getSubItems(Item item, CreativeTabs p_150895_2_, List p_150895_3_)
    {
		for(FleetClass f:FleetClass.values()){
	        p_150895_3_.add(new ItemStack(item, 1, FleetClass.getIDbyClass(f)));
		}
    }
	public String getUnlocalizedName(ItemStack item){
		return "item.fleetCard."+FleetClass.getClassByID(item.getItemDamage()).name();
	}
}
