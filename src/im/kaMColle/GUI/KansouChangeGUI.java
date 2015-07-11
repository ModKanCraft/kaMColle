package im.kaMColle.GUI;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import im.kaMColle.item.KamcolleFleetCard;
import im.kaMColle.network.MessageHandler;
import im.kaMColle.network.packet.KansouChangePacket;
import im.kaMColle.proxy.KamcolleClientProps;
import im.kaMColle.tileEntity.SallyBoardTileEntity;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class KansouChangeGUI extends GuiScreen {
	
	
	private ResourceLocation texture = new ResourceLocation(Kamcolle.ID, "textures/gui/KansouChangeGUI.png");
	private EntityPlayer player;
	private SallyBoardTileEntity blockTile;
	private ArrayList<KansouCard> kansouCard=new ArrayList<KansouCard>();
	private int page=1;
	private int maxPage;
	int guiX=0;
	int guiY=0;
	int animeSpeed=KamcolleClientProps.ANIME_SPEED;
	boolean isOpening=true;
	boolean isClosing=false;
	boolean shouldClosed=false;
	public static class KansouCard{
		ItemStack stack;
		public KansouCard(ItemStack stack){
			this.stack=stack;
		}
		public static ArrayList getFrom(ItemStack stack){
			ArrayList<KansouCard> cards=new ArrayList<KansouChangeGUI.KansouCard>(stack.stackSize);
			for(int i=0;i<stack.stackSize;i++){
				Kamcolle.LogInfo(i);
				cards.add(new KansouCard(stack));
			}
			return cards;
		}
	}
	
	public class KamcolleButton extends GuiButton{
		private int style=1;
		public int number=0;
		private boolean displayText=true;
		public KamcolleButton(int id,int x,int y,int w,int h,String s){
			super(id, x, y, w, h, s);
		}
		public KamcolleButton(int id,int x,int y,int w,int h){
			super(id, x, y, w, h,"");
		}
		public KamcolleButton(int id,int x,int y,int w,int h,int num){
			super(id,x,y,w,h,String.valueOf(num));
			this.number=num;
			this.style=0;
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
		public  KamcolleButton setDisplayText(KansouCard card){
				this.displayString=FleetClass.getClassLocalNameByID(card.stack.getItemDamage())+" "+card.stack.getDisplayName();
				this.displayText=true;
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
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
            	this.displayString=String.valueOf(number);
	            if(k==2)color=0xFF7F27;
	            if(number>maxPage){
	            	color=0xFFFFFF;
	            }
				else if(number==page){
					color=0x1EBEC1;
					this.displayString=String.format("%s%d",EnumChatFormatting.BOLD,number);
				}
				if(this.displayText)fontrenderer.drawString(
	            		this.displayString,
	            		this.xPosition + this.width/2,
	            		this.yPosition + (this.height - 8) / 2,
	            		color);
	            GL11.glPopMatrix();
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

	public KansouChangeGUI(EntityPlayer p,SallyBoardTileEntity t){
		this.player=p;
		this.blockTile=t;
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	public void initGui()
    {
		this.buttonList.clear();
		guiX=width;
        //每当界面被打开时调用
        //这里部署控件
		int x=this.guiX+getRelativeResolution(24);
		int y=getRelativeResolution(18);
		int w=getRelativeResolution(240);
		int h=getRelativeResolution(18);
		for(ItemStack item:this.player.inventory.mainInventory){
			if(item!=null&&item.getItem() instanceof KamcolleFleetCard)this.kansouCard.addAll(KansouCard.getFrom(item));
		}
		this.maxPage=(int) Math.ceil(kansouCard.size()/10F);
		this.buttonList.add(new KamcolleButton(0, x, y, w, getRelativeResolution(24),"はずす"));
		y+=getRelativeResolution(24);
		for(int i=1;i<=10;i++){
			this.buttonList.add(new KamcolleButton(i, x, y, w, h).setStyle(1));
			y+=h;
		}
		y+=getRelativeResolution(4);
		x=this.guiX+getRelativeResolution(142);
		for(int i=1;i<=5;i++){
			this.buttonList.add(new KamcolleButton(i+10, x+getRelativeResolution(i*20-60), y, getRelativeResolution(8), getRelativeResolution(8),i));
		}
    }
	private int getCurrentButtonNum(){
		return kansouCard.size()>=10?10:kansouCard.size()-(this.page-1)*10;
	}
	public void drawScreen(int par1, int par2, float par3)
    {
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
		int Dx=getRelativeResolution((int) (this.animeSpeed*par3));
		if(this.isOpening){
			this.guiX-=Dx;
			for(Object o:this.buttonList){
				((GuiButton)o).xPosition-=Dx;
			}
			if(this.guiX<=this.width-getRelativeResolution(248)){
				this.isOpening=false;
				int i=guiX-this.width+getRelativeResolution(248);
				this.guiX-=i;
				for(Object o:this.buttonList){
					((GuiButton)o).xPosition-=i;
				}
			}
		}
		if(this.isClosing){
			this.guiX+=Dx;
			for(Object o:this.buttonList){
				((GuiButton)o).xPosition+=Dx;
			}
			if(this.guiX>=this.width)this.shouldClosed=true;
		}
		if(this.shouldClosed==true){
			this.close();
			return;
		}
		KamcolleButton kbtn;
		KansouCard item;
        mc.renderEngine.bindTexture(texture);
        func_152125_a(this.guiX, this.guiY, 0, 0, 284, 239, getRelativeResolution(284), height, 284, 239);//参数分别为x,y,u,v,u宽度,v高度(即纹理中欲绘制区域的宽高),实际宽,实际高,纹理总宽,纹理总高.
        for(int i=1;i<=10;i++){
    		String s;
        	kbtn=(KamcolleButton)this.buttonList.get(i);
        	try{
            	item=kansouCard.get(getCardIndex(i-1));
            	kbtn.setDisplayText(item);
        	}catch(IndexOutOfBoundsException e){
        		kbtn.enabled=false;
        		kbtn.setDisplayText("");
        		continue;
        	}catch(NullPointerException e){
        		s="NULL";
        		kbtn.setDisplayText(s);
        	}
        	kbtn.enabled=true;
        }
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        super.drawScreen(par1,par2,par3);
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
        
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
    }
	
	protected void actionPerformed(GuiButton button){
		KamcolleButton btn=(KamcolleButton) button;
		if(btn.id==0){
			MessageHandler.INSTANCE.sendToServer(new KansouChangePacket((EntityPlayer) player, FleetClass.NULL,this.blockTile));
			closeGUI();
		}else if(btn.id<=10){
			MessageHandler.INSTANCE.sendToServer(new KansouChangePacket((EntityPlayer) player,
						FleetClass.getClassByID(kansouCard.get(getCardIndex(btn.id-1)).stack.getItemDamage()),
						this.blockTile));
			closeGUI();
		}else if(btn.id<=15){
			KamcolleButton[] kbtns=new KamcolleButton[5];
			for(int i=1;i<=5;i++){
				kbtns[i-1]=(KamcolleButton)this.buttonList.get(i+10);
			}
			if(btn.number<=maxPage){
				this.page=btn.number;
				if(page<3){
					for(int i=0;i<5;i++){
						kbtns[i].number=i+1;
					}
				}else if(maxPage-page<2){
					for(int i=4;i>=0;i--){
						kbtns[i].number=maxPage-4+i;
					}
				}else{
					kbtns[2].number=page;
					for(int i=1;i<=2;i++){
						kbtns[2+i].number=page+i;
						kbtns[2-i].number=page-i;
					}
				}
			}
		}
	}
	private void closeGUI(){
		this.isClosing=true;
	}
	private void close(){
		//实现关闭动画
		this.mc.displayGuiScreen((GuiScreen)null);
		this.mc.setIngameFocus();
		this.player.closeScreen();
	}
	@Override
	public void keyTyped(char key,int id){
		if(id==1){
			this.closeGUI();
		}
	}
	@Override
	public void onGuiClosed(){
		this.blockTile.isOccupied=false;
	}
	private int getCardIndex(int i){
		return i+(this.page-1)*10;
	}
	private int getRelativeResolution(int r){
		return (int) (height/239F*r);
	}
	
}
