package im.kaMColle.handleEvent;

import org.lwjgl.input.Keyboard;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegEventHandler;
import cn.annoreg.mc.RegEventHandler.Bus;
import cn.liutils.registry.KeyHandlerRegistration.RegKeyHandler;
import cn.liutils.util.helper.KeyHandler;
import im.kaMColle.Kamcolle;
import im.kaMColle.network.packet.KansouControlMessage;
import im.kaMColle.proxy.KamcolleClientProps;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
@Registrant
@RegEventHandler()
public class KeyboardInputHandler{
	static float lastFlotage=0;
	static float flotage=0;
	static boolean isFlowChanging;
	@RegKeyHandler(keyID = Keyboard.KEY_SPACE, name = "float")
	public static KeyHandler floatHandler=new KeyHandler() {
		@Override
		public void onKeyDown(){
			isFlowChanging=true;
		}
		@Override
		public void onKeyTick(){
			if(flotage<100)flotage+=0.5F;
			Kamcolle.LogInfo(flotage);
		}
		@Override
		public void onKeyUp(){
			isFlowChanging=false;
		}
	};
	@RegKeyHandler(keyID = Keyboard.KEY_LSHIFT, name = "sink")
	public static KeyHandler sinkHandler=new KeyHandler() {
		@Override
		public void onKeyDown(){
			isFlowChanging=true;
		}
		@Override
		public void onKeyTick(){
			if(flotage>-100)flotage-=0.5F;
			Kamcolle.LogInfo(flotage);
		}
		@Override
		public void onKeyUp(){
			isFlowChanging=false;
		}
	};
	@SubscribeEvent
	public void Handleflow(LivingUpdateEvent e){
		if(e.entity.equals(Minecraft.getMinecraft().thePlayer)){
			if(!isFlowChanging){
				if(flotage>0){
					if(flotage%5>0)flotage-=0.5F;
				}else if(flotage<0){
					if(flotage%5<0)flotage+=0.5F;
				}
			}
			if(flotage%5==0&&flotage!=lastFlotage){
				Kamcolle.MsgHandler.sendToServer(new KansouControlMessage((EntityPlayer) e.entity, (byte)flotage));
				lastFlotage=flotage;
			}
		}
	}
	public void HandleNormalKeyInput(KeyInputEvent e){
		while(KamcolleClientProps.aim.isPressed()){
			
		}
	}
}
