package im.kaMColle;

public enum ShipClass {
	SS,DD,CL,CA,CV,BB,AV,SSV,CAV,BBV,CLT,CVL,TEST,NOTVALUE;
	public static ShipClass toClassType(String str){
		try{
			return ShipClass.valueOf(str);
		}catch(Exception e){
			return NOTVALUE;
		}
	}
}
