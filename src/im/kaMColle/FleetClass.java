package im.kaMColle;


public enum FleetClass {
	//SS("SSKansou"),
	DD(KansouAttchments.DDTurret,KansouAttchments.TorpedoLauncher),
	CL(KansouAttchments.CLTurret,KansouAttchments.TorpedoLauncher),
	CA(KansouAttchments.CATurret,KansouAttchments.TorpedoLauncher),
	CV(KansouAttchments.CVLaunchPad),
	BB(KansouAttchments.BBTurret),
	AV,
	//SSV("SSKansou"),
	//CAV("CATurret","TorpedoLauncher","CAVLaunchPad"),
	BBV(KansouAttchments.BBTurret,KansouAttchments.BBVLaunchPad),
	CLT(KansouAttchments.CLTurret,KansouAttchments.TorpedoLauncher),
	//CVL(KansouType.CVLaunchPad),
	SCV,
	SBB,
	REI(KansouAttchments.BBTurret,KansouAttchments.TorpedoLauncher,KansouAttchments.BBVLaunchPad),//NANI?!!
	TEST(KansouAttchments.BBTurret,KansouAttchments.TorpedoLauncher),
	NULL();
	public KansouAttchments[] Kansou=null;
	private FleetClass(KansouAttchments... Kansou){
		this.Kansou=Kansou;
	}
}
