package me.kuckoobrain.PlayerTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utilities {
	
	public static String getCurrentChunkString(Location l, String sep) {
		Chunk chunk = l.getBlock().getChunk();
		return String.valueOf(chunk.getX()) + sep + String.valueOf(chunk.getZ());
	}
	
	public static void logMovement(Player p, Location l) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");
		String timestamp = df.format(new Date());
		
		String mainDirectory = "plugins/PlayerTracker";
		
		new File(mainDirectory).mkdir();
		
		File file = new File(mainDirectory + File.separator + p.getName().toLowerCase() + ".txt");
		
		try {
			file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            fw.write(timestamp);
            fw.write(";");
            fw.write(l.getWorld().getEnvironment().toString());
            fw.write(";");
            fw.write(String.valueOf(l.getBlock().getChunk().getX()));
            fw.write(";");
            fw.write(String.valueOf(l.getBlock().getChunk().getZ()));
            fw.write("\r\n");
            fw.close();
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
	}
	
	public static String upperCaseFirst(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
	}
	
}
