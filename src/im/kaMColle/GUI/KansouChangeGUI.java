package im.kaMColle.GUI;

import im.kaMColle.Kamcolle;
import im.kaMColle.item.KamcolleFleetCard;

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
	private ArrayList<ItemStack> FleetCard=new ArrayList<ItemStack>();
	private int page=1;
	
	
	
	public class KamcolleButton extends GuiButton{
		public int highLightColor=0xDE78BEB4;
		public boolean displayText=true;
		public int style=1;
		public KamcolleButton(int id,int x,int y,int w,int h,String s){
			super(id, x, y, w, h, s);
		}
		public KamcolleButton setHighLightColor(int color){
			this.highLightColor=color;
			return this;
		}
		public KamcolleButton setDisplayText(boolean b){
			this.displayText=b;
			return this;
		}
		public KamcolleButton setStyle(int style){
			this.style=style;
			return this;
		}
		public void drawButton(Minecraft mc, int mouseX, int mouseY){
			FontRenderer fontrenderer = mc.fontRenderer;
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            switch(style){
            case 0:
            	break;
            case 1:
            	if(k==2&&displayText){
	            	drawRect(this.xPosition,this.yPosition,this.xPosition+this.width,this.yPosition+this.height,this.highLightColor);
	            }
	            int h=height/239;
            	if(height/239<1)h=1;
            	drawRect(this.xPosition,this.yPosition+this.height-h,this.xPosition+this.width,this.yPosition+this.height,0xFFE0D7C8);
            	break;
            case 2:
            	//未完成
            	break;
            }
            
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            this.mouseDragged(mc, mouseX, mouseY);
            int color = 0x00000000;
            if(style==1&&k==2)color=0x00FFFFFF;
            if(displayText)fontrenderer.drawString(this.displayString, this.xPosition + (int)(2F*this.width/25), this.yPosition + (this.height - 8) / 2, color);
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
		int x=width-(int)(0.914F*(float)height/239*284);
		int y=(int)((float)height/239*18);
		int w=(int)(250d*height/239);
		int h=(int)(18d*height/239);
		int id=0;
		this.buttonList.add(new KamcolleButton(0, x, y, w, 24, "はずす"));
		y+=(int)((float)height/239*24);
		for(ItemStack item:Minecraft.getMinecraft().thePlayer.inventory.mainInventory){
			if(item!=null&&item.getItem() instanceof KamcolleFleetCard){
				this.FleetCard.add(item);
			}
		}
		int buttonNum=(FleetCard.size()-((page-1)*10))<=10?(FleetCard.size()-((page-1)*10)):10;
		for(int i=1;i<=10;i++){
			if(i<=buttonNum){
				int i0=(page-1)*10+i-1;
				this.buttonList.add(new KamcolleButton(i, x, y, w, h, FleetCard.get(i0).getDisplayName()));
			}else{
				this.buttonList.add(new KamcolleButton(i, x, y, w, h, "").setDisplayText(false));
			}
			y+=h;
		}
		for(int i=1;i<=5;i++){
			x=width-(int)((0.85F-0.1F*i)*(float)height/239*284);
			this.buttonList.add(new KamcolleButton(i+10, x, y, w, h, i+"").setStyle(0));
		}
		//this.buttonList.add(new KamcolleButton(16, x, y, w, h, "").setStyle(2));
    }
	public void drawScreen(int par1, int par2, float par3)
    {
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        mc.renderEngine.bindTexture(texture);
        func_152125_a(width-(int)((float)height/239*284), 0, 0, 0, 284, 239, (int)((float)height/239*284), height, 284, 239);//参数分别为x,y,u,v,u宽度,v高度(即纹理中欲绘制区域的宽高),实际宽,实际高,纹理总宽,纹理总高.

        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        super.drawScreen(par1,par2,par3);
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
        mc.fontRenderer.drawString(par1+","+par2,0,0, 0xFFFFFFFF);
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
    }
}
