package im.kaMColle.GUI;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class KansouCard{
	ItemStack stack;
	public KansouCard(ItemStack stack){
		this.stack=stack;
	}
	public String getDisplayName(){
		String s=String.format("%s %s",FleetClass.getClassLocalNameByID(this.stack.getItemDamage()),this.stack.getDisplayName());
		return s;
	}
	public String getAdditionText() {
		// TODO Auto-generated method stub
		return null;
	}
}