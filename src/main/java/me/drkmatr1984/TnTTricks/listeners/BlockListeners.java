package me.drkmatr1984.TnTTricks.listeners;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import me.drkmatr1984.TnTTricks.TnTTricks;

public class BlockListeners implements Listener
{
	
	private TnTTricks plugin;
	
	public BlockListeners(TnTTricks plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
    public void EntityChangeBlockEvent(final EntityChangeBlockEvent e) {
        if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(e.getBlock().getWorld().getName()) && e.getEntity() instanceof FallingBlock) {
            final Block f = e.getBlock();
            if (e.getEntity().isOnGround()) {
                f.getWorld().playEffect(f.getLocation(), Effect.STEP_SOUND, 100);
                if(!this.plugin.getConfig().getBoolean("FlyingBlocks.AllowFlyingBlockSettle")){
                	if (this.plugin.containsBlock(e.getEntity().getUniqueId()))
                    {
                		e.setCancelled(true);
                		this.plugin.removeEntityBlock(e.getEntity().getUniqueId());               
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onBlockPlaceEvent(final BlockPlaceEvent e) {
        if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(e.getPlayer().getWorld().getName())) {
            final Block t = e.getBlockPlaced();
            if (t.getType() == Material.TNT) {
                t.getWorld().playEffect(t.getLocation(), Effect.MOBSPAWNER_FLAMES, 100, 100);
            }
            if(t.getType() == Material.DISPENSER){
            	this.plugin.addDispenserLocation(e.getPlayer(), t.getLocation());
            }
        }
    }
    
    @EventHandler
    public void onBlockBreakEvent(final BlockBreakEvent e) {
        if (this.plugin.getConfig().getStringList("AllowedWorlds").contains(e.getPlayer().getWorld().getName())) {
            final Block t = e.getBlock();
            if (t.getType() == Material.TNT) {
                t.getWorld().playEffect(t.getLocation(), Effect.SMOKE, 100, 100);
            }
        }
    }
}