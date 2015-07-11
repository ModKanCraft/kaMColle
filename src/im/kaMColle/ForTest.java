package im.kaMColle;

import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ForTest {
	@SubscribeEvent
	public void CheckAtSaving(SaveToFile e){
		Kamcolle.LogInfo("Saving:"+e.entityPlayer.getEntityData().getString("FleetClass"));
	}
	@SubscribeEvent
	public void CheckAtLoading(LoadFromFile e){
		Kamcolle.LogInfo("Loading:"+e.entityPlayer.getEntityData().getString("FleetClass"));
	}
}
