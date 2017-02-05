package me.drkmatr1984.TnTTricks.events;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CreeperExplodeEvent extends EntityExplodeEvent implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();
	private Creeper creeper;
    public CreeperExplodeEvent(Entity what, Location location, List<Block> blocks, float yield) {
		super(what, location, blocks, yield);
		this.creeper = (Creeper) what;	
	}
	
	public Creeper getCreeper()
	{
		if(this.creeper==null)
			return null;
		return this.creeper;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
}