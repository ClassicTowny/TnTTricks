package me.drkmatr1984.TnTTricks.utils;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import me.drkmatr1984.TnTTricks.TnTTricks;
import me.drkmatr1984.TnTTricks.events.ExplosionLaunchBlockEvent;
public class explodeUtils{
	
	@SuppressWarnings("deprecation")
	public static boolean explodeBlocks(World world, Entity entity, List<Block> blockList, Plugin plugin){
		HashMap<FallingBlock,Block> blockToFallingBlock = new HashMap<FallingBlock,Block>(); 
		final float x = -2.0f + (float)(Math.random() * 5.0);
	    final float y = -3.0f + (float)(Math.random() * 7.0);
	    final float z = -2.5f + (float)(Math.random() * 6.0);
		boolean dropItem = true;
		Vector velocity = new Vector(x, y, z);
		Material current = null;
	    for (final Block b : blockList) {
	       current = b.getType();
	       if (b.getType() == Material.SNOW) {
	          b.setType(Material.AIR);
	       }
	       if (b.getType() == Material.STONE) {
	          b.setType(Material.COBBLESTONE);
	       }
	       if (b.getType() == Material.LEAVES_2) {
	          b.setType(Material.LEAVES);
	       }
	       if (b.getType() == Material.GRASS) {
	          b.setType(Material.DIRT);
	       }
	       if (b.getType() != Material.TNT) {	    	  
	          final FallingBlock fallingBlock = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());	          
	          blockToFallingBlock.put(fallingBlock, b);          
	       }
	       if(current!=null){
	    	   b.setType(current);
	       }	       
	    }
	    ExplosionLaunchBlockEvent event = new ExplosionLaunchBlockEvent(velocity, dropItem, entity, blockToFallingBlock.keySet());
	    Bukkit.getPluginManager().callEvent(event);
	    if(event.isCancelled()){
	    	for(FallingBlock f : blockToFallingBlock.keySet()){
	    		f.setDropItem(false);
	    		f.remove();
	    	}
	    	return false;
	    }else{
	    	world.playEffect(entity.getLocation(), Effect.MOBSPAWNER_FLAMES, 100, 100);
    		world.playEffect(entity.getLocation(), Effect.ENDER_SIGNAL, 100, 100);
    		world.playEffect(entity.getLocation(), Effect.MOBSPAWNER_FLAMES, 100, 100);
	    	for(FallingBlock f : blockToFallingBlock.keySet()){
	    		Block b = blockToFallingBlock.get(f);    		
	    		b.setType(Material.AIR);
		        b.getWorld().playEffect(b.getLocation(), Effect.SMOKE, 100, 100);
		        b.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 100, 100);
	    		f.setVelocity(event.getVelocity());
	    		f.setDropItem(event.isDropItem());
	    		if(plugin instanceof TnTTricks){
		        	  TnTTricks tricks = (TnTTricks) plugin;
		        	  tricks.addEntityUUID(f.getUniqueId());
		        }
	    	}
	    	return true;
	    }
	}
}
