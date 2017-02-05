package me.drkmatr1984.TnTTricks.config;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import me.drkmatr1984.TnTTricks.TnTTricks;
import me.drkmatr1984.TnTTricks.serialization.DispenserLocation;
import me.drkmatr1984.TnTTricks.serialization.SetSerialization;

public class DispenserLocations{
	public TnTTricks plugin;
	private File usersFile;
	private FileConfiguration users;
	private Set<DispenserLocation> dispenserLocations = new HashSet<DispenserLocation>();
	//public List<String> disabledExplodePlayers = new ArrayList<String>();
	
	public DispenserLocations(TnTTricks plugin){
		this.plugin = plugin;
	}
	  
	public void loadDispenserLocations(){
	   if (usersFile == null) {
	       usersFile = new File(plugin.getDataFolder(), "dispenserlocations.yml");
	   }
	   if (!usersFile.exists()) {           
	       plugin.saveResource("dispenserlocations.yml", false);
	   }
	   users = YamlConfiguration.loadConfiguration(usersFile);
	   if(users.getString("DispenserLocations.locations")!=null){
		   try {
			   dispenserLocations = SetSerialization.fromBase64(users.getString("DispenserLocations.locations"), users.getInt("DispenserLocations.size"));
			   if(dispenserLocations==null){
				   dispenserLocations = new HashSet<DispenserLocation>();
			   }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	   }
	}
	  
	public void saveDispenserLocations(){
		if(dispenserLocations!=null)
		{
			users.set("DispenserLocations.locations",SetSerialization.toBase64(dispenserLocations));
			users.set("DispenserLocations.size",dispenserLocations.size());
		}
		//if(disabledExplodePlayers!=null)
		//{
	    //  users.set("DisabledExplodePlayers",disabledExplodePlayers);
		//}
		if(usersFile.exists())
			usersFile.delete();
		try {
			users.save(usersFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			usersFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Set<DispenserLocation> getDispenserLocations()
	{
		return this.dispenserLocations;
	}
	
	public void setDispenserLocations(Set<DispenserLocation> dispenserLocations)
	{
		this.dispenserLocations = dispenserLocations;
	}
}