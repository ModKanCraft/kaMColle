package im.kaMColle.GUI;

import im.kaMColle.Kamcolle;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class KansouChangeGUI extends GuiScreen {
	
	
	private ResourceLocation texture = new ResourceLocation(Kamcolle.ID, "textures/gui/KansouChangeGUI.png");
	private InventoryPlayer PlayerInv;
	private ArrayList FleetCard;
	
	
	
	public class KamcolleButton extends GuiButton{
		
		public KamcolleButton(int id,int x,int y,String s){
			super(id, x, y, 200, 20, s);
		}
		public void drawButton(Minecraft mc, int mouseX, int mouseY){
	        if (this.visible)
	        {
	            FontRenderer fontrenderer = mc.fontRenderer;
	            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
	            int k = this.getHoverState(this.field_146123_n);
	            GL11.glEnable(GL11.GL_BLEND);
	            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	            GL11.glDisable(GL11.GL_TEXTURE_2D);
	            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	            if(k==2)drawRect(this.xPosition,this.xPosition+this.width,this.yPosition,this.yPosition+this.height,0x0078BEB4);
	            GL11.glEnable(GL11.GL_TEXTURE_2D);
	            this.mouseDragged(mc, mouseX, mouseY);
	            int color = 0x00000000;
	            if(k==2)color=0x00FFFFFF;
	            fontrenderer.drawString(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, color);
	        }
	    }
		
	}

	
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	public void initGui()
    {
        //每当界面被打开时调用
        //这里部署控件
		this.PlayerInv=Minecraft.getMinecraft().thePlayer.inventory;
		for(ItemStack item:PlayerInv.mainInventory){
			
		}
    }
	public void drawScreen(int par1, int par2, float par3)
    {
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        mc.renderEngine.bindTexture(texture);
        func_152125_a(width*2/3, 0, 0, 0, 900/height*width/3, 900, width/3, height, 1440, 900);//参数分别为x,y,u,v,u宽度,v高度(即纹理中欲绘制区域的宽高),实际宽,实际高,纹理总宽,纹理总高.
        
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        super.drawScreen(par1,par2,par3);
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
        
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
    }
}
