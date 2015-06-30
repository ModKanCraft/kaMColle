package im.kaMColle.GUI;

import im.kaMColle.Kamcolle;
import im.kaMColle.item.KamcolleFleetCard;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class KansouChangeGUI extends GuiScreen {
	
	
	private ResourceLocation texture = new ResourceLocation(Kamcolle.ID, "textures/gui/KansouChangeGUI.png");
	private EntityPlayer player;
	private ArrayList<ItemStack> FleetCard=new ArrayList<ItemStack>();
	private int page=1;
	private int maxPage;
	
	
	
	public class KamcolleButton extends GuiButton{
		private int style=1;
		private boolean displayText=true;
		public KamcolleButton(int id,int x,int y,int w,int h,String s){
			super(id, x, y, w, h, s);
		}
		public KamcolleButton(int id,int x,int y,int w,int h){
			super(id, x, y, w, h,"");
		}
		public  KamcolleButton setDisplayText(String s){
			if(s==null||s.equals("")){
				this.displayText=false;
			}else{
				this.displayString=s;
				this.displayText=true;
			}
			return this;
		}
		public void drawButton(Minecraft mc, int mouseX, int mouseY){
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            drawButtonAsStyle(this.style);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            this.mouseDragged(mc, mouseX, mouseY);
	    }
		public KamcolleButton setStyle(int style){
			this.style=style;
			return this;
		}
		private void drawButtonAsStyle(int style){
			FontRenderer fontrenderer = mc.fontRenderer;
            int k = this.getHoverState(this.field_146123_n);
            int color=0x000000;
			switch(style){
			case 0:
				//我有点搞不明白，为啥这句随便画个透明的东西可以解决字体渲染错误的问题
				drawRect(
						this.xPosition,
						this.yPosition,
						this.xPosition+this.width,
						this.yPosition+this.height+1,
						0x00000000);
				
	            if(k==2)color=0xFF7F27;
	            if(this.displayText)fontrenderer.drawString(
	            		this.displayString,
	            		this.xPosition + this.width/2,
	            		this.yPosition + (this.height - 8) / 2,
	            		color);
				break;
			case 1:
	            if(k==2){
		            if(this.displayText)drawRect(
		            		this.xPosition,
		            		this.yPosition,
		            		this.xPosition+this.width,
		            		this.yPosition+this.height+1,
		            		0xDD78BEB4);
		            color=0xFFFFFF;
	            }
	            if(this.id!=0)drawRect(
	            		this.xPosition,
	            		this.yPosition,
	            		this.xPosition+this.width,
	            		this.yPosition+1,
	            		0xFFC4B294);
	            drawRect(
	            		this.xPosition,
	            		this.yPosition+this.height,
	            		this.xPosition+this.width,
	            		this.yPosition+this.height+1,
	            		0xFFC4B294);
	            if(this.displayText)fontrenderer.drawString(this.displayString, this.xPosition + this.width*2/25, this.yPosition + (this.height - 8) / 2, color);
				break;
			case 2:
				break;
			}
		}
	}

	public KansouChangeGUI(EntityPlayer p){
		this.player=p;
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	public void initGui()
    {
		this.buttonList.clear();
        //每当界面被打开时调用
        //这里部署控件
		int x=width-getRelativeResolution(258);
		int y=getRelativeResolution(18);
		int w=getRelativeResolution(240);
		int h=getRelativeResolution(18);
		for(ItemStack item:this.player.inventory.mainInventory){
			if(item!=null&&item.getItem() instanceof KamcolleFleetCard)this.FleetCard.add(item);
		}
		this.maxPage=(int) Math.ceil(FleetCard.size()/10F);
		this.buttonList.add(new KamcolleButton(0, x, y, w, getRelativeResolution(24)).setDisplayText("はずす"));
		y+=getRelativeResolution(24);
		for(int i=1;i<=10;i++){
			this.buttonList.add(new KamcolleButton(i, x, y, w, h).setDisplayText(i+""));
			y+=h;
		}
		y+=getRelativeResolution(4);
		x=width-getRelativeResolution(142);
		for(int i=1;i<=5;i++){
			this.buttonList.add(new KamcolleButton(i+10, x+getRelativeResolution(i*20-60), y, getRelativeResolution(8), getRelativeResolution(8)).setStyle(0).setDisplayText(i+""));
		}
    }
	private int getCurrentButtonNum(){
		return FleetCard.size()>=10?10:FleetCard.size()-(this.page-1)*10;
	}
	public void drawScreen(int par1, int par2, float par3)
    {
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        mc.renderEngine.bindTexture(texture);
        func_152125_a(width-getRelativeResolution(284), 0, 0, 0, 284, 239, getRelativeResolution(284), height, 284, 239);//参数分别为x,y,u,v,u宽度,v高度(即纹理中欲绘制区域的宽高),实际宽,实际高,纹理总宽,纹理总高.
        for(int i=1;i<=10;i++){
        	KamcolleButton kbtn=(KamcolleButton)this.buttonList.get(i);
    		String s;
        	try{
        		s=FleetCard.get(getCardIndex(i-1)).getDisplayName();
        	}catch(IndexOutOfBoundsException e){
        		s="";
        	}
        	kbtn.setDisplayText(s);
        }
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        super.drawScreen(par1,par2,par3);
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
        
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
    }
	private int getCardIndex(int i){
		return i+(this.page-1)*10;
	}
	private int getRelativeResolution(int r){
		return (int) (height/239F*r);
	}
}
