package im.kaMColle.event;
import im.kaMColle.FleetClass;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.Event;


public class FleetClassChangeEvent extends Event{
	public final EntityPlayer player;
	public final FleetClass fleetClass;
	
	public FleetClassChangeEvent(EntityPlayer player,FleetClass Class){
		super();
		this.player=player;
		this.fleetClass=Class;
	}
}
