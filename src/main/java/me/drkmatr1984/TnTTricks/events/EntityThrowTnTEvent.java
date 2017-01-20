package me.drkmatr1984.TnTTricks.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;

public class EntityThrowTnTEvent extends Event implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();
	private Entity entity;
	private TNTPrimed tnt;
	private Vector velocity;
	private boolean cancelled = false;
	
	public EntityThrowTnTEvent(Entity entity, TNTPrimed tnt, Vector velocity)
	{
		this.entity = entity;
		this.tnt = tnt;
		this.velocity = velocity;
	}
	
	public Entity getEntity()
	{
		return this.entity;
	}
	
	public TNTPrimed getTnT()
	{
		
		return this.tnt;
	}
	
	public Vector getVelocity()
	{
		return this.velocity;
	}
	
	public void setVelocity(Vector velocity)
	{
		this.velocity = velocity;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;		
	}
}