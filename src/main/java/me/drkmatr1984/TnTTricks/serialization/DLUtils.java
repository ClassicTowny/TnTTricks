package me.drkmatr1984.TnTTricks.serialization;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DLUtils
{
	public static Set<DispenserLocation> dispensers = new HashSet<DispenserLocation>();
	
	public DLUtils(Set<DispenserLocation> dispensers){
		DLUtils.dispensers = dispensers;
	}
	
	public static void addDispenserLocation(Player p, Location loc){
    	DispenserLocation l = new DispenserLocation(p.getUniqueId(), loc);
    	dispensers.add(l);
    }
    
    public static void removeDispenserLocation(Location loc){
    	if(containsDispenserLocation(loc))
    	{
    		Set<DispenserLocation> temp = dispensers;
    		for(DispenserLocation l : dispensers){
        		if(l.getLocation().equals(loc)){
        			temp.remove(l);
        		}
        	}
    		dispensers = temp;
    	}
    }
    
    public static boolean containsDispenserLocation(Location loc){
    	for(DispenserLocation l : dispensers){
    		if(l.getLocation().equals(loc)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public static Set<DispenserLocation> getDispenserLocationsbyPlayer(Player p){
    	Set<DispenserLocation> temp = new HashSet<DispenserLocation>();
    	for(DispenserLocation l : dispensers){
    		if(l.getPlayerUUID().toString() == p.getUniqueId().toString()){
    			temp.add(l);
    		}
    	}
    	return temp;
    }
    
    public static DispenserLocation getDispenserLocation(Location loc)
    {
    	for(DispenserLocation l : dispensers){
    		if(l.getLocation().equals(loc)){
    			return l;
    		}
    	}
    	return null;
    }
}