package me.drkmatr1984.TnTTricks.events;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.projectiles.BlockProjectileSource;

public class TnTExplodeEvent extends EntityExplodeEvent implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();
    private TNTPrimed tnt;
    private Entity what;
    private Location location;
    private List<Block> blocks;
    private float yield;
    
	public TnTExplodeEvent(Entity what, Location location, List<Block> blocks, float yield) {
		super(what, location, blocks, yield);			
		this.what = what;
	    this.location = location;
	    this.blocks = blocks;
	    this.yield = yield;
		this.tnt = (TNTPrimed) what;
	}

	public Entity getTrueSource() {
		if(this.tnt!=null){
			Entity source = this.tnt.getSource();
			if(source!=null){
                if(source instanceof TNTPrimed){
    				//source = getChainTnTSource(source);
    			}
    			if(source instanceof Projectile){
    				Projectile projectile = (Projectile) source;
    				if(projectile.getShooter() instanceof Player){
    					source = (Player) projectile.getShooter();
    				}
    				if(projectile.getShooter() instanceof Monster){
    					Monster mons = (Monster) projectile.getShooter();
    					if(mons.getTarget() instanceof Player)
    					{
    						source = ((Player)mons.getTarget());
    					}
    				}
    				if(projectile.getShooter() instanceof BlockProjectileSource){
    					Block b = ((BlockProjectileSource)projectile.getShooter()).getBlock();
    					if(b.getState() instanceof Dispenser){
    						
    					}
    				}
    				
    			} 			
    		}
    		if(tnt.getCustomName()!=null)
    		{
    			if(tnt.getCustomName().contains("source: "))
    			{
    				String uuid = tnt.getCustomName();
    				uuid = uuid.replace("source: ", "");
    				source = Bukkit.getServer().getPlayer(UUID.fromString(uuid));
    			}
    		}
    		return source;
		}
		return null;
	}
	
	public Entity getSource() {
		if(this.tnt.equals(null))
			return null;
		return this.tnt.getSource();
	}
	
	public TNTPrimed getTNTPrimed(){
		if(this.tnt.equals(null))
			return null;
		return this.tnt;
	}
	
	private Entity getChainTnTSource(Entity tnt){
    	Entity next = tnt;
    	TNTPrimed nTnt = null;
		do
		{
			if(next!=null){
				if(next instanceof TNTPrimed){
					nTnt = (TNTPrimed) next;
					next = nTnt.getSource();				
				}
			}else{
				if(nTnt.getCustomName()!=null)
    			{
    				if(nTnt.getCustomName().contains("source: "))
    				{
    					String uuid = nTnt.getCustomName();
    					next = Bukkit.getServer().getPlayer(UUID.fromString(uuid.replace("source: ", "")));
    				}
    			}
			}
		}while(next instanceof TNTPrimed && next!=null);
		return next;
    }

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
}