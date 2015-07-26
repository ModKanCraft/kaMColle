package im.kaMColle.GUI;

import im.kaMColle.FleetClass;
import im.kaMColle.Kamcolle;
import im.kaMColle.item.KamcolleFleetCard;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cn.liutils.util.helper.Font;

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
	
	
	public class KamcolleButton extends GuiButton{
		private int style=1;
		public int number=0;
		private boolean displayText=true;
		public String additionString;
		public KamcolleButton(int i) {
			super(i, 0, 0, "");
		}
		public KamcolleButton setPos(int x,int y){
			this.xPosition=x;
			this.yPosition=y;
			return this;
		}
		public KamcolleButton setWH(int w,int h){
			this.width=w;
			this.height=h;
			return this;
		}
		public KamcolleButton setDisplayText(String s){
			if(s==null||s.equals("")){
				this.displayText=false;
			}else{
				this.displayString=s;
				this.displayText=true;
			}
			return this;
		}
		public  KamcolleButton setDisplayText(KansouCard card){
				this.displayString=card.getDisplayName();
				this.additionString=card.getAdditionText();
				this.displayText=true;
			return this;
		}
		public KamcolleButton setStyle(int style){
			this.style=style;
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
		
		private void drawButtonAsStyle(int style){
			FontRenderer fontrenderer = mc.fontRenderer;
			fontrenderer.FONT_HEIGHT=20;
            int k = this.getHoverState(this.field_146123_n);
            int color=0x000000;
            boolean isBold=false;
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
					isBold=true;
				}
				if(this.displayText){
					int size=getRelativeResolution(9);
					if(isBold){
						size=getRelativeResolution(10);
					}
					Font.font.draw(
		            		this.displayString,
		            		this.xPosition + this.width/2,
		            		this.yPosition - this.height/4,
		            		size,
		            		color);
				}
	            GL11.glPopMatrix();
				break;
			case 1:
	            if(k==2){
		            if(this.displayText)
		            	drawRect(
		            		this.xPosition,
		            		this.yPosition,
		            		this.xPosition+this.width,
		            		this.yPosition+this.height+1,
		            		0xDD78BEB4);
		            color=0xFFFFFF;
	            }
	            if(this.id!=0)
	            	drawRect(
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
	            if(this.displayText)
	            	Font.font.draw(this.displayString,this.xPosition + this.width*2/25, this.yPosition + (this.height - 8) / 2+1, getRelativeResolution(9), color);
	            	//fontrenderer.drawString(this.displayString, this.xPosition + this.width*2/25, this.yPosition + (this.height - 8) / 2+1, color);
	            if(this.additionString!=null)
	            	Font.font.draw(this.additionString, this.xPosition + this.width*2/25 + getRelativeResolution(100), this.yPosition + (this.height - 8) / 2+1, getRelativeResolution(9), color);
				break;
			case 2:
				break;
			}
			fontrenderer.FONT_HEIGHT=9;
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
	private KamcolleButton getKBtnByID(int id){
		return (KamcolleButton) this.buttonList.get(id);
	}
	public void initGui()
    {
		for(ItemStack item:this.player.inventory.mainInventory){
			if(item!=null&&item.getItem() instanceof KamcolleFleetCard)this.kansouCard.addAll(getFrom(item));
		}
		this.maxPage=(int) Math.ceil(kansouCard.size()/10F);
		this.buttonList.clear();
		guiX=width;
        //每当界面被打开时调用
        //这里部署控件
		for(int i=0;i<=15;i++)this.buttonList.add(new KamcolleButton(i));
		setButtonWH();
		setButtonStyle();
		for(int i=1;i<=5;i++){
			this.getKBtnByID(i+10).number=i;
		}
    }
	private void setButtonPos(){
		int x=this.guiX+getRelativeResolution(24);
		int y=getRelativeResolution(18);
		int h=getRelativeResolution(18);
		this.getKBtnByID(0).setPos(x, y);
		y+=getRelativeResolution(24);
		for(int i=1;i<=10;i++){
			this.getKBtnByID(i).setPos(x, y);
			y+=h;
		}
		y+=(height-getRelativeResolution(224))/2;
		x=this.guiX+getRelativeResolution(120);
		for(int i=1;i<=5;i++){
			this.getKBtnByID(i+10).setPos(x+getRelativeResolution(i*20-60), y);
		}
	}
	private void setButtonWH(){
		int w=getRelativeResolution(240);
		int h=getRelativeResolution(18);
		this.getKBtnByID(0).setWH(w, getRelativeResolution(24));
		for(int i=1;i<=10;i++){
			this.getKBtnByID(i).setWH(w, h);
		}
		for(int i=11;i<=15;i++){
			this.getKBtnByID(i).setWH(getRelativeResolution(8), getRelativeResolution(8));
		}
	}
	private void setButtonStyle(){
		for(int i=0;i<=10;i++){
			this.getKBtnByID(i).setStyle(1);
		}
		for(int i=11;i<=15;i++){
			this.getKBtnByID(i).setStyle(0);
		}
	}
	private void setButtonText(){
		KansouCard item;
		KamcolleButton kbtn;
		this.getKBtnByID(0).setDisplayText("はずす");
		for(int i=1;i<=10;i++){
			kbtn=this.getKBtnByID(i);
        	try{
            	item=kansouCard.get(getCardIndex(i-1));
            	kbtn.setDisplayText(item);
        	}catch(IndexOutOfBoundsException e){
        		kbtn.enabled=false;
        		kbtn.setDisplayText("");
        		continue;
        	}catch(NullPointerException e){
        		kbtn.setDisplayText("NULL");
        	}
        	kbtn=(KamcolleButton)this.buttonList.get(i);
        	kbtn.enabled=true;
		}
		for(int i=1;i<=5;i++){
			this.getKBtnByID(i+10).displayString=String.valueOf(this.getKBtnByID(i+10).number);
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
			if(this.guiX<=this.width-getRelativeResolution(248))this.isOpening=false;
		}else if(this.isClosing){
			this.guiX+=Dx;
			if(this.guiX>=this.width)this.close();
		}else{
			this.guiX=this.width-getRelativeResolution(248);
		}
		setButtonPos();
		setButtonText();
        mc.renderEngine.bindTexture(texture);
        func_152125_a(this.guiX, this.guiY, 0, 0, 284, 239, getRelativeResolution(284), height, 284, 239);//参数分别为x,y,u,v,u宽度,v高度(即纹理中欲绘制区域的宽高),实际宽,实际高,纹理总宽,纹理总高.
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        super.drawScreen(par1,par2,par3);
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
        
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
    }
	
	protected void actionPerformed(GuiButton button){
		KamcolleButton btn=(KamcolleButton) button;
		if(btn.id==0){
			Kamcolle.MsgHandler.sendToServer(new KansouChangePacket((EntityPlayer) player, FleetClass.NULL,this.blockTile));
			closeGUI();
		}else if(btn.id<=10){
			Kamcolle.MsgHandler.sendToServer(new KansouChangePacket((EntityPlayer) player,
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
	
	private ArrayList getFrom(ItemStack stack){
		ArrayList<KansouCard> cards=new ArrayList<KansouCard>(stack.stackSize);
		for(int i=0;i<stack.stackSize;i++){
			if(stack.getTagCompound()==null)
				cards.add(new KansouCard(stack));
			else
				cards.add(new KanmusuCard(stack));
		}
		return cards;
	}
}
