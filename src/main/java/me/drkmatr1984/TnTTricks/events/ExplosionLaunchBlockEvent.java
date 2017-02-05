package me.drkmatr1984.TnTTricks.events;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;

public class ExplosionLaunchBlockEvent extends Event implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();
	private final float x = -2.0f + (float)(Math.random() * 5.0);
    private final float y = -3.0f + (float)(Math.random() * 7.0);
    private final float z = -2.5f + (float)(Math.random() * 6.0);
    private Vector velocity = new Vector(x, y, z);
    private boolean dropItem = true;
    private Entity entity;
    private Set<FallingBlock> fallingBlocks = new HashSet<FallingBlock>();
    private World world;
    private boolean cancelled = false;
	
	public ExplosionLaunchBlockEvent(Vector velocity, boolean dropItem, Entity entity, Set<FallingBlock> fallingBlocks)
	{
		this.velocity = velocity;
		this.dropItem = dropItem;
		this.entity = entity;
		this.fallingBlocks = fallingBlocks;
		this.world = entity.getWorld();
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public boolean isDropItem() {
		return dropItem;
	}

	public void setDropItem(boolean dropItem) {
		this.dropItem = dropItem;
	}

	public Set<FallingBlock> getFallingBlocks() {
		return this.fallingBlocks;
	}

	public void setFallingBlocks(Set<FallingBlock> fallingBlocks) {
		this.fallingBlocks = fallingBlocks;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public World getWorld() {
		return this.world;
	}
}