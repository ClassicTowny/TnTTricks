package me.drkmatr1984.TnTTricks.utils;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import me.drkmatr1984.TnTTricks.TnTTricks;

public class explodeUtils{
	
	@SuppressWarnings("deprecation")
	public static void explodeBlocks(World world, Entity entity, List<Block> blockList, Plugin plugin){
		world.playEffect(entity.getLocation(), Effect.MOBSPAWNER_FLAMES, 100, 100);
		world.playEffect(entity.getLocation(), Effect.ENDER_SIGNAL, 100, 100);
		world.playEffect(entity.getLocation(), Effect.MOBSPAWNER_FLAMES, 100, 100);
	    for (final Block b : blockList) {
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
	          final float x = -2.0f + (float)(Math.random() * 5.0);
	          final float y = -3.0f + (float)(Math.random() * 7.0);
	          final float z = -2.5f + (float)(Math.random() * 6.0);
	          final FallingBlock fallingBlock = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
	          fallingBlock.setVelocity(new Vector(x, y, z));
	          fallingBlock.setDropItem(true);
	          if(plugin instanceof TnTTricks){
	        	  TnTTricks tricks = (TnTTricks) plugin;
	        	  tricks.addEntityUUID(fallingBlock.getUniqueId());
	          }
	          
	          b.setType(Material.AIR);
	          b.getWorld().playEffect(b.getLocation(), Effect.SMOKE, 100, 100);
	          b.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 100, 100);
	        }
	     }
	}
}
