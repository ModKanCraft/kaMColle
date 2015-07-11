package im.kaMColle.handleEvent;

import im.kaMColle.Kamcolle;
import im.kaMColle.network.MessageHandler;
import im.kaMColle.network.packet.KansouControlMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyboardInputHandler {
	byte t=0;
	static final byte MAX_V=10;
	EntityPlayer me=Minecraft.getMinecraft().thePlayer;
	@SubscribeEvent
	public void listenKeyJumpAndSneak(KeyInputEvent e){
		if(Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed()){
			t+=1;
		}
		if(Minecraft.getMinecraft().gameSettings.keyBindSneak.getIsKeyPressed()){
			t-=1; 
		}
		Kamcolle.LogInfo(t);
		MessageHandler.INSTANCE.sendToServer(new KansouControlMessage(Minecraft.getMinecraft().thePlayer,t));
	}
	private int addFlow(int i){
		t+=i;
		if(t>0)t=(byte) Math.min(MAX_V, t);
		else if(t<0)Math.max(-MAX_V, t);
		return t;
	}
}
