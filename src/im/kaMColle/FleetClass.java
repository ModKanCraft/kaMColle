package im.kaMColle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.util.StatCollector;


public enum FleetClass {
	SS(),
	DD(KansouAttchments.DDTurret,KansouAttchments.TorpedoLauncher),
	CL(/*KansouAttchments.CLTurret,*/KansouAttchments.TorpedoLauncher),
	CA(KansouAttchments.CATurret,KansouAttchments.TorpedoLauncher),
	CV(KansouAttchments.CVLaunchPad),
	BB(KansouAttchments.BBTurret),
	AV,
	SSV(KansouAttchments.SSVLauncher),
	//CAV("CATurret","TorpedoLauncher","CAVLaunchPad"),
	BBV(KansouAttchments.BBTurret,KansouAttchments.BBVLaunchPad),
	CLT(KansouAttchments.CLTurret,KansouAttchments.TorpedoLauncher),
	//CVL(KansouType.CVLaunchPad),
	SCV,
	SBB,
	REI(KansouAttchments.BBTurret,KansouAttchments.TorpedoLauncher,KansouAttchments.BBVLaunchPad),//NANI?!!
	TEST(KansouAttchments.Test),
	NULL();
	public KansouAttchments[] Kansou=null;
	private static HashMap<Integer,FleetClass> l=new HashMap();
	private static HashMap<FleetClass,Integer> l0=new HashMap();
	private static Random r=new Random();
	private FleetClass(KansouAttchments... Kansou){
		this.Kansou=Kansou;
	}
	static{
		int id=0;
		for(FleetClass f:FleetClass.values()){
			l.put(id,f);
			l0.put(f, id);
			id++;
		}
	}
	public static FleetClass getRandomClass(){
		return (FleetClass) l.get(r.nextInt(l.size()));
	}
	public static FleetClass getClassByID(int i){
		if(l.containsKey(i))return l.get(i);
		return NULL;
	}
	public static int getIDbyClass(FleetClass f){
		if(l0.containsKey(f))return l0.get(f);
		return -1;
	}
	public static String getClassLocalName(FleetClass f){
		String s="FleetClass."+f.name();
		if(StatCollector.canTranslate(s))return StatCollector.translateToLocal(s);
		return s;
	}
	public static String getClassLocalNameByID(int i){
		FleetClass f=getClassByID(i);
		return getClassLocalName(f);
	}
}
