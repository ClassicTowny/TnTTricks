package me.drkmatr1984.TnTTricks.events;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.projectiles.BlockProjectileSource;

import me.drkmatr1984.TnTTricks.serialization.DLUtils;

public class TnTExplodeEvent extends EntityExplodeEvent implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();
    private TNTPrimed tnt;
    
    public TnTExplodeEvent(Entity what, Location location, List<Block> blocks, float yield) {
		super(what, location, blocks, yield);			
		this.tnt = (TNTPrimed) what;
	}

	public Entity getTrueSource() {
		if(this.tnt!=null){
			Entity source = this.tnt.getSource();
			if(source!=null){
                if(source instanceof TNTPrimed && Bukkit.getServer().getPluginManager().isPluginEnabled("TnTTricks")){
    				source = getChainTnTSource((TNTPrimed)source);
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
    						if(DLUtils.containsDispenserLocation(b.getLocation())){
    							source = (DLUtils.getDispenserLocation(b.getLocation())).getPlayer();
    						}   						 
    					}
    				}    				
    			}
    			if(source instanceof Creeper){
    				Creeper creep = (Creeper) source;
    				source = creep.getTarget();
    			}
    		}
    		if(tnt.getCustomName()!=null)
    		{
    			if(tnt.getCustomName().contains("source: "))
    			{
    				String uuid = tnt.getCustomName();
    				uuid = uuid.replace("source: ", "");
    				source = Bukkit.getServer().getEntity(UUID.fromString(uuid));
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
	
	private Entity getChainTnTSource(TNTPrimed tnt){
    	TNTPrimed nTnt = tnt;
    	Entity finalEntity = null;
		do
		{
			if(!(nTnt.getSource() instanceof TNTPrimed)){
				if(nTnt.getSource()==null){
					if(nTnt.getCustomName()!=null)
	    			{
	    				if(nTnt.getCustomName().contains("source: "))
	    				{
	    					String uuid = nTnt.getCustomName();
	    					finalEntity = Bukkit.getServer().getEntity(UUID.fromString(uuid.replace("source: ", "")));
	    				}
	    			}
				}else{
					finalEntity = nTnt.getSource();
				}				
			}
			
		}while(nTnt instanceof TNTPrimed);
		return finalEntity;
    }

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
}