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
import me.drkmatr1984.TnTTricks.events.CreeperExplodeEvent;
import me.drkmatr1984.TnTTricks.events.TnTExplodeEvent;
import me.drkmatr1984.TnTTricks.utils.explodeUtils;

public class EntityExplodeListeners implements Listener
{
	private TnTTricks plugin;
	
	public EntityExplodeListeners(TnTTricks plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void onEntityTnTExplode(final EntityExplodeEvent e){
    	if(e.getEntity() instanceof TNTPrimed){
    		TnTExplodeEvent event = new TnTExplodeEvent(e.getEntity(), e.getLocation(), e.blockList(), e.getYield());     	
            Bukkit.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
    	}
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void onEntityCreeperExplode(final EntityExplodeEvent e){
    	if(e.getEntity() instanceof Creeper){
    		CreeperExplodeEvent event = new CreeperExplodeEvent(e.getEntity(), e.getLocation(), e.blockList(), e.getYield());
    		Bukkit.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onTnTExplode(TnTExplodeEvent event) {
    	Entity entity = event.getEntity();
    	World world = entity.getWorld();
    	if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(event.getLocation().getWorld().getName())) {
    		explodeUtils.explodeBlocks(world, entity, event.blockList(), plugin);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCreeperExplode(CreeperExplodeEvent event) {
    	Entity entity = event.getEntity();
    	World world = entity.getWorld();
    	float explosionPower = 8.0f;
    	if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(event.getLocation().getWorld().getName())) {
    		explodeUtils.explodeBlocks(world, entity, event.blockList(), plugin);
    		if(this.plugin.getConfig().getString("Explosions.CreeperExplosionPower")!=null){
    			explosionPower = Float.parseFloat((this.plugin.getConfig().getString("Explosions.CreeperExplosionPower"))+"f");
    		}
            event.setCancelled(true);
            event.getLocation().getWorld().createExplosion(event.getLocation(), explosionPower); 		
    	}  	
    }
    
    @EventHandler
    public void TNTPower(final ExplosionPrimeEvent event) {
    	if(event.getEntityType() == EntityType.PRIMED_TNT){
    		float explosionPower = 7.0f;
            if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(event.getEntity().getWorld().getName())) {
            	if(this.plugin.getConfig().getString("Explosions.TnTExplosionPower")!=null){
            		explosionPower = Float.parseFloat((this.plugin.getConfig().getString("Explosions.TnTExplosionPower"))+"f");
            	}
            	event.setRadius(explosionPower);
            }
    	}   	
    }
} 