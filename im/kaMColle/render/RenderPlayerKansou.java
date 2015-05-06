package im.kaMColle.render;

import im.kaMColle.FleetClass;
import im.kaMColle.OBJmodels.KamcolleOBJModelResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RenderPlayerKansou {
	ModelBoat model=new ModelBoat();
	KamcolleOBJModelResourceManager modelManager=KamcolleOBJModelResourceManager.getManager();
	/*[C]LI-狼:
	 *所以如果你要渲染世界里其他玩家的效果，还得自己处理下变换，不过也不难
	 *GL11.glTranslated(
	 *otherPlayer.posX - RenderManager.renderPosX, 
	 *otherPlayer.posY - RenderManager.renderPosY, 
	 *otherPlayer.posZ - RenderManager.renderPosZ);
	 */
	 /*
	  * 好吧其实我没能自己实现这玩意
	  * 但是Test2就不用我自己实现啊，果断以后用Test2
	  */
	@SubscribeEvent
	public void renderPlayerKansouTest(RenderPlayerEvent.Specials.Post event){
		if(event.entityPlayer.inventory.hasItem(Items.boat)){
			return;
		}
		modelManager.RederModel(FleetClass.TEST);
	}
}
