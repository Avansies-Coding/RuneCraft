package us.avansies.RuneCraft;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RCFront extends JavaPlugin {
	
	public static File plugDir;
	public static File config;
	public static File playersFold;
	
//	For enabling the plugin
	public void onEnable() {
//		Set the value of plugDir to the data folder corresponding to the plugin
		plugDir = Bukkit.getServer().getPluginManager().getPlugin("RuneCraft").getDataFolder();
//		Check to see if the folder exists
		if(!plugDir.exists()) {
//			Create a directory out of the plugin directory
			plugDir.mkdir();
		}
//		Set the value of config to a file within the plugin directory by the name of "config.yml"
		config = new File(plugDir.getPath() + File.separator + "config.yml");
//		Check to see if config.yml exists
		if(!config.exists()) {
			try {
//				Create a file out of config
				config.createNewFile();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		playersFold = new File(plugDir.getPath() + File.separator + "players");
		if(!playersFold.exists()) {
			playersFold.mkdir();
		}
		Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
		for(Player targPlayer : Bukkit.getServer().getOnlinePlayers()) {
			Methods.createPlayerFile(targPlayer);
		}
		
	}
	
}
