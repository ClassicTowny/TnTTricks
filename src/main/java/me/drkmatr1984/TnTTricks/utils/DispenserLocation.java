package me.drkmatr1984.TnTTricks.utils;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DispenserLocation implements Serializable
{
	private static final long serialVersionUID = -5944092517430475805L;
	
	private Player player;
	private Location location;
	
	public DispenserLocation(Player player, Location location)
	{
		this.player = player;
		this.location = location;
	}
	
	public Location getLocation()
	{
		return this.location;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
}