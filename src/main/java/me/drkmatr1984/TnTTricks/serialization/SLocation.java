package me.drkmatr1984.TnTTricks.serialization;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;

public class SLocation implements Serializable
{
	private String worldUUID = "";
	private Double x = 0.0;
	private Double y = 0.0;
	private Double z = 0.0;
	
	private static final long serialVersionUID = -5944092517430475805L;
	public SLocation(World world, Double x, Double y, Double z)
	{
		this.worldUUID = world.getUID().toString();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Location getLocation()
	{
		Server server = Bukkit.getServer();
		for(World w : server.getWorlds())
		{
			if(w.getUID().toString() == this.worldUUID)
			{
				return new Location(getWorld(), x, y, z);
			}
		}
		return null;
	}
	
	public World getWorld()
	{
		Server server = Bukkit.getServer();
		for(World w : server.getWorlds())
		{
			if(w.getUID().toString() == this.worldUUID)
			{
				return server.getWorld(this.worldUUID);
			}
		}
		return null;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getZ()
	{
		return this.z;
	}
	
}