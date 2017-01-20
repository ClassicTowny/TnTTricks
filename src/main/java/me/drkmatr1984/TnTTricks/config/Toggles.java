package me.drkmatr1984.TnTTricks.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.drkmatr1984.TnTTricks.TnTTricks;

public class Toggles{
	public TnTTricks plugin;
	private File usersFile;
	private FileConfiguration users;
	private List<String> disabledThrowPlayers = new ArrayList<String>();
	//public List<String> disabledExplodePlayers = new ArrayList<String>();
	
	public Toggles(TnTTricks plugin){
		this.plugin = plugin;
	}
	  
	public void loadUserList(){
	   if (usersFile == null) {
	       usersFile = new File(plugin.getDataFolder(), "toggles.yml");
	   }
	   if (!usersFile.exists()) {           
	       plugin.saveResource("toggles.yml", false);
	   }
	   users = YamlConfiguration.loadConfiguration(usersFile);
	   disabledThrowPlayers = users.getStringList("DisabledThrowPlayers");
	   //disabledExplodePlayers = users.getStringList("DisabledExplodePlayers");
	}
	  
	public void saveUserList(){
		if(disabledThrowPlayers!=null)
		{
			users.set("DisabledThrowPlayers",disabledThrowPlayers);
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
	
	public List<String> getDisabledThrowPlayers()
	{
		return this.disabledThrowPlayers;
	}
	
	public void setDisabledThrowPlayers(List<String> disabled)
	{
		this.disabledThrowPlayers = disabled;
	}
	
	public void addDisabledThrowPlayer(Player p){
		this.disabledThrowPlayers.add(p.getUniqueId().toString());
	}
	
	public void removeDisabledThrowPlayer(Player p){
		this.disabledThrowPlayers.remove(p.getUniqueId().toString());
	}
	
	public boolean hasDisabledThrowPlayer(Player p){
		if(this.disabledThrowPlayers.contains(p.getUniqueId().toString())){
			return true;
		}
		return false;
	}
}
