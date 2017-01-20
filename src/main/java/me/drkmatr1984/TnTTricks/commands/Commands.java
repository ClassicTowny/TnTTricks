package me.drkmatr1984.TnTTricks.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.drkmatr1984.TnTTricks.TnTTricks;

public class Commands implements CommandExecutor
{
	private TnTTricks plugin;
	
	public Commands(TnTTricks plugin){
		this.plugin = plugin;
	}
	
	@Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("tnttricks")) {
        	if(args.length>0){
        		if(args.length==1){
        			if(args[0].equals("help")){
        				displayHelp(sender);
        				return true;
        			}
        			if(args[0].equals("reload")){
        				if (sender.hasPermission("tnttricks.reload") || !(sender instanceof Player)) {
        					this.plugin.reloadConfig();
        	                sender.sendMessage(ChatColor.AQUA + "TnTTricks Reloaded!!!");
        	            }
        	            else {
        	                sender.sendMessage(ChatColor.DARK_RED + "You dont have permission!!!");
        	            }
        				return true;
        			}
        		}else if(args.length==2){
        			if(sender instanceof Player){
        				Player p = (Player) sender;
            			if(args[0].equals("toggle")){
            				if(args[1].equals("throw")){
            					if(this.plugin.toggles.hasDisabledThrowPlayer(p)){
                					sender.sendMessage(ChatColor.GRAY + "Throwing TnT" + ChatColor.GREEN + " toggled on");
                					this.plugin.toggles.removeDisabledThrowPlayer(p);
                				}else{
                					sender.sendMessage(ChatColor.GRAY + "Throwing TnT" + ChatColor.RED + " toggled off");
                					this.plugin.toggles.addDisabledThrowPlayer(p);
                				}
                               	return true;
            				}
            				return true;
            			}                  
            		}
        		}
        		displayHelp(sender);
        		return true;
        	}
        	displayHelp(sender);
    		return true;
        }	
        return true;
    }
    
    public void displayHelp(CommandSender sender){
    	if(sender instanceof Player){
    		//if(sender.hasPermission("tnttricks.toggle.explode"))
    		//    sender.sendMessage("/tnttricks toggle explode  - Toggle Explosion Effect off for yourself");
    		if(sender.hasPermission("tnttricks.toggle.throw")){
    			sender.sendMessage("/tnttricks toggle throw  - Toggle Thowing TnT off for Yourself");
    		}
    		if(sender.hasPermission("tnttricks.reload")){
    			sender.sendMessage("/tnttricks reload  - Reload the Configs");
    		}
    	}else{
    		sender.sendMessage("/tnttricks reload  - Reload the Configs");
    	}
    }
}