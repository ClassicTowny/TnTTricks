package me.drkmatr1984.TnTTricks;

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
	private static Plugin plugin;
	private static Config config;
	
	public Toggles toggles;
    
    public void onEnable() {
    	plugin = this;
    	getCommand("tnttricks").setExecutor(new Commands(this));
        this.getServer().getPluginManager().registerEvents(new EntityExplodeListeners(), plugin);
        this.getServer().getPluginManager().registerEvents(new EntityThrowListeners(this), plugin);
        this.getServer().getPluginManager().registerEvents(new BlockListeners(this), plugin);
        this.getConfig().options().copyDefaults();
        config = new Config(this);
        toggles = new Toggles(this);
        toggles.loadUserList();
    }
    
    public void onDisable() {
    	toggles.saveUserList();
    }
       
    public static Config getTnTInstanceConfig(){
    	return config;
    }

	public static Plugin getInstance() {
		return plugin;
	}
}
