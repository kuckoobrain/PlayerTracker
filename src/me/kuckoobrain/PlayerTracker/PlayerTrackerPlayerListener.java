package me.kuckoobrain.PlayerTracker;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerTrackerPlayerListener extends PlayerListener {
	
	public static PlayerTracker plugin;
	
	public PlayerTrackerPlayerListener(PlayerTracker instance) {
		plugin = instance;
	}
	
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		String from = Utilities.getCurrentChunkString(e.getFrom(), ",");
		String to = Utilities.getCurrentChunkString(e.getTo(), ",");
		
		if(!from.equals(to)) {
			Utilities.logMovement(p, p.getLocation());
		}
	}
}
