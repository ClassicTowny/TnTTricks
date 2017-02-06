package me.drkmatr1984.TnTTricks.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class Config
{
	private Plugin plugin;
	private static float tntExplosionPower = 7.0f;
	private static float creeperExplosionPower = 8.0f;
	private static List<World> disabledWorlds = new ArrayList<World>();
	
	public Config(Plugin plugin)
	{
		this.plugin = plugin;
		if(Bukkit.getServer().getPluginManager().isPluginEnabled("TnTTricks")){
			this.plugin.saveDefaultConfig();
		}
		if(this.plugin.getConfig().getString("Explosions.TnTExplosionPower")!=null){
			Config.tntExplosionPower = Float.parseFloat(this.plugin.getConfig().getString("Explosions.TnTExplosionPower"));
		}
		if(this.plugin.getConfig().getString("Explosions.CreeperExplosionPower")!=null){
			Config.creeperExplosionPower = Float.parseFloat(this.plugin.getConfig().getString("Explosions.CreeperExplosionPower"));
		}
		if(this.plugin.getConfig().getStringList("DisabledWorlds")!=null){
			List<String> disabledWorlds = this.plugin.getConfig().getStringList("DisabledWorlds");
			for(String s : disabledWorlds){
				if(Bukkit.getServer().getWorld(s)!=null){
					Config.disabledWorlds.add(Bukkit.getServer().getWorld(s));
				}
			}
		}
	}

	public static float getTntExplosionPower() {
		return Config.tntExplosionPower;
	}

	public static void setTntExplosionPower(float tntExplosionPower) {
		Config.tntExplosionPower = tntExplosionPower;
	}

	public static float getCreeperExplosionPower() {
		return Config.creeperExplosionPower;
	}

	public static void setCreeperExplosionPower(float creeperExplosionPower) {
		Config.creeperExplosionPower = creeperExplosionPower;
	}
	
	public static void setDisabledWorlds(List<World> disabledWorlds){
		Config.disabledWorlds = disabledWorlds;
	}
	
	public static List<World> getDisabledWorlds(){
		return Config.disabledWorlds;
	}
	
}