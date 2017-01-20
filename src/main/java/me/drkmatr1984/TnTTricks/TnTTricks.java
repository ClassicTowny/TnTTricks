package me.drkmatr1984.TnTTricks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import me.drkmatr1984.TnTTricks.commands.Commands;
import me.drkmatr1984.TnTTricks.config.Toggles;
import me.drkmatr1984.TnTTricks.listeners.BlockListeners;
import me.drkmatr1984.TnTTricks.listeners.EntityExplodeListeners;
import me.drkmatr1984.TnTTricks.listeners.EntityThrowListeners;
import me.drkmatr1984.TnTTricks.utils.DispenserLocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class TnTTricks extends JavaPlugin implements Listener
{
	List<String> entityList = new ArrayList<String>();
	Set<DispenserLocation> dispensers = new HashSet<DispenserLocation>();
	private static Plugin plugin;
	
	public Toggles toggles;
	
    public static void main(final String[] args) {
        System.out.println(-5.0f + (float)(Math.random() * 11.0));
    }
    
    public void onEnable() {
    	plugin = this;
    	getCommand("tnttricks").setExecutor(new Commands(this));
        this.getServer().getPluginManager().registerEvents((Listener)this, this);
        this.getServer().getPluginManager().registerEvents(new EntityExplodeListeners(this), plugin);
        this.getServer().getPluginManager().registerEvents(new EntityThrowListeners(this), plugin);
        this.getServer().getPluginManager().registerEvents(new BlockListeners(this), plugin);
        this.getConfig().options().copyDefaults();
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
    
    public void addDispenserLocation(Player p, Location loc){
    	DispenserLocation l = new DispenserLocation(p, loc);
    	dispensers.add(l);
    }
    
    public void removeDispenserLocation(Player p, Location loc){
    	if(containsDispenserLocation(p, loc))
    	{
    		Set<DispenserLocation> temp = dispensers;
    		for(DispenserLocation l : dispensers){
        		if(l.getPlayer().equals(p) && l.getLocation().equals(loc)){
        			temp.remove(l);
        		}
        	}
    		dispensers = temp;
    	}
    }
    
    public boolean containsDispenserLocation(Player p, Location loc){
    	for(DispenserLocation l : dispensers){
    		if(l.getPlayer().equals(p) && l.getLocation().equals(loc)){
    			return true;
    		}
    	}
    	return false;
    }

	public static Plugin getInstance() {
		return plugin;
	}
}
