package me.drkmatr1984.TnTTricks.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import me.drkmatr1984.TnTTricks.TnTTricks;
import me.drkmatr1984.TnTTricks.config.Config;
import me.drkmatr1984.TnTTricks.events.CreeperExplodeEvent;
import me.drkmatr1984.TnTTricks.events.TnTExplodeEvent;
import me.drkmatr1984.TnTTricks.utils.explodeUtils;

public class EntityExplodeListeners implements Listener
{
	private TnTTricks plugin;
	
	public EntityExplodeListeners(TnTTricks plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityTnTExplode(final EntityExplodeEvent e){
    	if(e.getEntity() instanceof TNTPrimed){
    		TnTExplodeEvent event = new TnTExplodeEvent(e.getEntity(), e.getLocation(), e.blockList(), e.getYield());     	
            Bukkit.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
            	e.setCancelled(true);
                return;
            }
    	}
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityCreeperExplode(final EntityExplodeEvent e){
    	if(e.getEntity() instanceof Creeper){
    		CreeperExplodeEvent event = new CreeperExplodeEvent(e.getEntity(), e.getLocation(), e.blockList(), e.getYield());
    		Bukkit.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
            	e.setCancelled(true);
                return;
            }
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onTnTExplode(TnTExplodeEvent event) {
    	Entity entity = event.getEntity();
    	World world = entity.getWorld();
    	if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(event.getLocation().getWorld().getName())) {
    		if(!explodeUtils.explodeBlocks(world, entity, event.blockList(), plugin)){
    			event.setCancelled(true);
    			return;
    		}
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCreeperExplode(CreeperExplodeEvent event) {
    	Entity entity = event.getEntity();
    	World world = entity.getWorld();
    	if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(event.getLocation().getWorld().getName())) {
    		if(explodeUtils.explodeBlocks(world, entity, event.blockList(), plugin)){
    			event.setCancelled(true);
                event.getLocation().getWorld().createExplosion(event.getLocation(), Config.getCreeperExplosionPower());
    		} 		
    	}  	
    }
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void TNTPower(final ExplosionPrimeEvent event) {
    	if(event.getEntityType() == EntityType.PRIMED_TNT){
            if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(event.getEntity().getWorld().getName())) {				
            	event.setRadius(Config.getTntExplosionPower());
            }
    	}   	
    }
} 