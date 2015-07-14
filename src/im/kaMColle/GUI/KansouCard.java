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
	public static ArrayList getFrom(ItemStack stack){
		ArrayList<KansouCard> cards=new ArrayList<KansouCard>(stack.stackSize);
		for(int i=0;i<stack.stackSize;i++){
			Kamcolle.LogInfo(i);
			cards.add(new KansouCard(stack));
		}
		return cards;
	}
	public String getDisplayName(){
		String s=String.format("%s\040%s",FleetClass.getClassLocalNameByID(this.stack.getItemDamage()),this.stack.getDisplayName());
		return s;
	}
}