package im.kaMColle.handleEvent;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegEventHandler;
import cn.annoreg.mc.RegEventHandler.Bus;
import im.kaMColle.Kamcolle;
import im.kaMColle.network.packet.KansouControlMessage;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
@Registrant
@RegEventHandler(Bus.FML)
public class KeyboardInputHandler {
	byte t=0;
	static final byte MAX_V=10;
	@SubscribeEvent
	public void listenKeyJumpAndSneak(KeyInputEvent e){
		EntityPlayer me=Minecraft.getMinecraft().thePlayer;
		if(me.worldObj.isAABBInMaterial(me.boundingBox, Material.water)){
			if(Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed()){
				addFlow(1);
			}
			if(Minecraft.getMinecraft().gameSettings.keyBindSneak.getIsKeyPressed()){
				addFlow(-1); 
			}
		}
		
		Kamcolle.LogInfo(t);
		Kamcolle.MsgHandler.sendToServer(new KansouControlMessage(Minecraft.getMinecraft().thePlayer,t));
	}
	private int addFlow(int i){
		t+=i;
		if(t>MAX_V)t=MAX_V;
		else if(t<-MAX_V)t=-MAX_V;
		return t;
	}
}
