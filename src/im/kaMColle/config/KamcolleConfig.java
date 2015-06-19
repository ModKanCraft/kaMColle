package im.kaMColle.config;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

public class KamcolleConfig {
	private Configuration config;
	public KamcolleConfig(File configFile)
    {
        config = new Configuration(configFile);
        if(!configFile.exists())
        {
            try
            {
                configFile.createNewFile();
            }
            catch(IOException e)
            {
                System.out.println(e);
                return;
            }
        }
        config.load();
    }
	public void save()
    {
        this.config.save();
    }
	public void load(){
		this.config.load();
	}
	public String getGeneralProperties(String PropertyName, String DefaultValue)
    {
        return this.config.get("general", PropertyName, DefaultValue).getString();
    }
	public String toString(){
		return config.toString();
	}
}