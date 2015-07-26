package im.kaMColle.GUI;

import im.kaMColle.FleetClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class KanmusuCard extends KansouCard {

	public KanmusuCard(ItemStack stack) {
		super(stack);
	}
	@Override
	public String getDisplayName(){
		String s=String.format("%s %s",FleetClass.getClassLocalNameByID(this.stack.getItemDamage()),this.stack.stackTagCompound.getString("FleetName"));
		s=Minecraft.getMinecraft().fontRenderer.trimStringToWidth(s, 100);
		return s;
	}
	public String getAdditionText(){
		//（舰种+ +舰名）-11 +等级 +耐久 +火力 +雷装 +对空 +速力
		String s=String.format("%3d %3d %3d %3d %3d  %s",67,75,94,0,67,"高速");
		return s;
	}
}
