package me.drkmatr1984.TnTTricks.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;

import me.drkmatr1984.TnTTricks.events.EntityThrowTnTEvent;

public class throwUtils
{
	public static boolean throwTNT(Entity entity)
    {
    	World world = entity.getWorld();
    	TNTPrimed tnt = (TNTPrimed)world.spawn(entity.getLocation(), TNTPrimed.class);
       	Vector playerDirection = entity.getLocation().getDirection();
       	Vector smallerVector = playerDirection.multiply(2D);
       	EntityThrowTnTEvent throwEvent = new EntityThrowTnTEvent(entity, tnt, smallerVector);
       	Bukkit.getServer().getPluginManager().callEvent(throwEvent);
       	if(throwEvent.isCancelled()){
    	   	tnt.remove();
    	   	return false;
       	}
        tnt.setVelocity(throwEvent.getVelocity());
        tnt.setCustomName("source: " + entity.getUniqueId().toString());
        tnt.setCustomNameVisible(false);           
        return true;
    }
}