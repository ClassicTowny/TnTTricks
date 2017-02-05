package me.drkmatr1984.TnTTricks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.drkmatr1984.TnTTricks.commands.Commands;
import me.drkmatr1984.TnTTricks.config.Config;
import me.drkmatr1984.TnTTricks.config.Toggles;
import me.drkmatr1984.TnTTricks.listeners.BlockListeners;
import me.drkmatr1984.TnTTricks.listeners.EntityExplodeListeners;
import me.drkmatr1984.TnTTricks.listeners.EntityThrowListeners;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class TnTTricks extends JavaPlugin
{
	List<String> entityList = new ArrayList<String>();
	private static Plugin plugin;
	private static Config config;
	
	public Toggles toggles;
    
    public void onEnable() {
    	plugin = this;
    	getCommand("tnttricks").setExecutor(new Commands(this));
        this.getServer().getPluginManager().registerEvents(new EntityExplodeListeners(this), plugin);
        this.getServer().getPluginManager().registerEvents(new EntityThrowListeners(this), plugin);
        this.getServer().getPluginManager().registerEvents(new BlockListeners(this), plugin);
        this.getConfig().options().copyDefaults();
        config = new Config(this);
        toggles = new Toggles(this);
        toggles.loadUserList();
        this.saveDefaultConfig();
    }
    
    public void onDisable() {
    	toggles.saveUserList();
    }  
    
    public void addEntityUUID(UUID id)
    {
        String uuid = id.toString();
        this.entityList.add(uuid);
    }
 
    public void removeEntityBlock(UUID id)
    {
        String uuid = id.toString();
        if (this.entityList.contains(uuid)) this.entityList.remove(uuid);
    }
 
    public boolean containsBlock(UUID id)
    {
        String uuid = id.toString();
        if (this.entityList.contains(uuid))
        {
            return true;
        }
        return false;
    }
       
    public static Config getTnTInstanceConfig(){
    	return config;
    }

	public static Plugin getInstance() {
		return plugin;
	}
}
