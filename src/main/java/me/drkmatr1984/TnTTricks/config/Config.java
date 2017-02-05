package me.drkmatr1984.TnTTricks.config;

import org.bukkit.plugin.Plugin;

public class Config
{
	private Plugin plugin;
	private static float tntExplosionPower = 7.0f;
	private static float creeperExplosionPower = 8.0f;
	
	public Config(Plugin plugin)
	{
		this.plugin = plugin;
		if(this.plugin.getConfig().getString("Explosions.TnTExplosionPower")!=null){
			tntExplosionPower = Float.parseFloat(this.plugin.getConfig().getString("Explosions.TnTExplosionPower"));
		}
		if(this.plugin.getConfig().getString("Explosions.CreeperExplosionPower")!=null){
			creeperExplosionPower = Float.parseFloat(this.plugin.getConfig().getString("Explosions.CreeperExplosionPower"));
		}
		
		
	}

	public static float getTntExplosionPower() {
		return tntExplosionPower;
	}

	public static void setTntExplosionPower(float tntExplosionPower) {
		Config.tntExplosionPower = tntExplosionPower;
	}

	public static float getCreeperExplosionPower() {
		return creeperExplosionPower;
	}

	public static void setCreeperExplosionPower(float creeperExplosionPower) {
		Config.creeperExplosionPower = creeperExplosionPower;
	}
	
	
}