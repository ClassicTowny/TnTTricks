package me.drkmatr1984.TnTTricks.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import me.drkmatr1984.TnTTricks.TnTTricks;
import me.drkmatr1984.TnTTricks.config.Config;
import me.drkmatr1984.TnTTricks.events.EntityThrowTnTEvent;
import me.drkmatr1984.TnTTricks.utils.throwUtils;

public class EntityThrowListeners implements Listener
{
	private TnTTricks plugin;
	
	public EntityThrowListeners(TnTTricks plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item.getType() == Material.TNT){
        	if(player.hasPermission("tnttricks.throw") && (event.getAction() == Action.RIGHT_CLICK_AIR) && !(plugin.toggles.hasDisabledThrowPlayer(player))) {
        		if(throwUtils.throwTNT(player)){
        			return;
        		}else{
        			event.setCancelled(true);
        		}
        	}
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityThrowTnt(EntityThrowTnTEvent event) {  	
    	if(!(event.getEntity() instanceof Player)){
    		event.setCancelled(true);
    		return;
    	}
    	if(Config.getDisabledWorlds().contains(event.getEntity().getWorld())){
    		event.setCancelled(true);
    		return;
    	}   		
    	Player player = (Player) event.getEntity();
        ItemStack item = player.getInventory().getItemInMainHand();
    	if(item.getType() == Material.TNT){ 
    		if(player.getGameMode()!=GameMode.CREATIVE){
    			if(item.getAmount() == 1) {
    				player.getInventory().remove(item);
    			} else {
    				item.setAmount(item.getAmount() - 1);
    			}
    		}
    	}
    }
}