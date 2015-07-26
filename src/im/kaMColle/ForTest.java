package im.kaMColle;

import cn.annoreg.core.Registrant;
import cn.annoreg.mc.RegEventHandler;
import cn.annoreg.mc.RegEventHandler.Bus;
import im.kaMColle.item.KamcolleFleetCard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Registrant
@RegEventHandler
public class ForTest {
	boolean finish=false;
	@SubscribeEvent
	public void test1(LivingUpdateEvent e){
		if(e.entity instanceof EntityPlayer){
			EntityPlayer p=(EntityPlayer) e.entity;
			for(ItemStack i:p.inventory.mainInventory){
				if((!this.finish)&&i!=null&&i.getItem() instanceof KamcolleFleetCard&&i.getItemDamage()==FleetClass.getIDbyClass(FleetClass.CLT)){
					if(i.getTagCompound()==null)i.stackTagCompound=new NBTTagCompound();
					i.getTagCompound().setString("FleetName", "大井改二");
					this.finish=true;
				}
			}
		}
	}
}
