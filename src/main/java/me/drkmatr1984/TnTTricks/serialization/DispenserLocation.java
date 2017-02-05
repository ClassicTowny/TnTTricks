package me.drkmatr1984.TnTTricks.serialization;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class DispenserLocation implements Serializable
{
	private static final long serialVersionUID = -5944092517430475805L;
	
	private UUID playerUUID;
	private SLocation location;
	
	public DispenserLocation(UUID playerUUID, Location location)
	{
		this.playerUUID = playerUUID;
		this.location = new SLocation(location.getWorld(), ((Integer)location.getBlockX()).doubleValue(), ((Integer)location.getBlockY()).doubleValue(), ((Integer)location.getBlockZ()).doubleValue());
	}
	
	public Location getLocation()
	{
		return this.location.getLocation();
	}
	
	public OfflinePlayer getOfflinePlayer()
	{
		OfflinePlayer op = Bukkit.getServer().getOfflinePlayer(this.playerUUID);
		if (op != null && op.hasPlayedBefore()) {
		    return op;
		} else {
		    return null;
		}
	}
	
	public Player getPlayer()
	{
		OfflinePlayer op = Bukkit.getServer().getOfflinePlayer(this.playerUUID);
		if (op != null && op.hasPlayedBefore() && op.isOnline()) {
		    return op.getPlayer();
		} else {
		    return null;
		}
	}
	
	public UUID getPlayerUUID()
	{
		return this.playerUUID;
	}
}