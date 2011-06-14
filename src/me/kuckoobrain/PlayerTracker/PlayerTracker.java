package me.kuckoobrain.PlayerTracker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerTracker extends JavaPlugin {
	
	private final PlayerTrackerPlayerListener playerListener = new PlayerTrackerPlayerListener(this);
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println(pdf.getName() + " " + pdf.getVersion() + " disabled");
	}

	@Override
	public void onEnable() {
		registerHooks();
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println(pdf.getName() + " " + pdf.getVersion() + " enabled");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("chunk")) {
			if(args.length != 0) {
				return false;
			}
			
			p.sendMessage(
					ChatColor.BLUE 
					+ "You are currently in chunk " 
					+ Utilities.getCurrentChunkString(p.getLocation(), ",")
					+ " (" 
					+ p.getLocation().getWorld().getEnvironment().toString().toLowerCase()
					+ ")");
			
			return true;
		}
		
		return false;
	}
	
	private void registerHooks() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, this.playerListener, Priority.Highest, this);
	}

}
