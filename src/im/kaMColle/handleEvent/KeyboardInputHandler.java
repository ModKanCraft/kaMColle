package im.kaMColle.handleEvent;

import im.kaMColle.Kamcolle;
import im.kaMColle.network.MessageHandler;
import im.kaMColle.network.packet.KansouControlMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyboardInputHandler {
	@SubscribeEvent
	public void listenKeyJumpAndSneak(KeyInputEvent e){
		EntityPlayer me=Minecraft.getMinecraft().thePlayer;
		byte t=0;
		while(Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()){
			t+=1;
			if(t%20==19)MessageHandler.INSTANCE.sendToServer(new KansouControlMessage(Minecraft.getMinecraft().thePlayer,t));
			Kamcolle.LogInfo(t);
		}
		while(Minecraft.getMinecraft().gameSettings.keyBindSneak.isPressed()){
			t+=1;
			if(t%20==19)MessageHandler.INSTANCE.sendToServer(new KansouControlMessage(Minecraft.getMinecraft().thePlayer,(byte) -t));
		}
}

}
