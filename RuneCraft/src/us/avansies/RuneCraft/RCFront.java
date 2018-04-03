package us.avansies.RuneCraft;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RCFront extends JavaPlugin {
	
	public static String adminPrefix;
	public static File plugDir;
	public static File config;
	public static File playersFold;
	
//	For enabling the plugin
	public void onEnable() {
		adminPrefix = "" + ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Rune" + ChatColor.RED + "Admin" + ChatColor.GRAY + "]" + ChatColor.RESET + "";
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
			YamlConfiguration conYAML = YamlConfiguration.loadConfiguration(config);
			conYAML.createSection("locations");
			conYAML.createSection("locations.selection");
			conYAML.set("locations.selection.world", "world");
			conYAML.set("locations.selection.x", 0);
			conYAML.set("locations.selection.y", Bukkit.getServer().getWorld("world").getHighestBlockYAt(0, 0));
			conYAML.set("locations.selection.z", 0);
			conYAML.set("locations.selection.pitch", 180);
			conYAML.set("locations.selection.yaw", 0);
			try {
				conYAML.save(config);
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
			if(!Methods.hasDataFile(targPlayer)) {
				Methods.createPlayerFile(targPlayer);
				Methods.teleportTo("selection", targPlayer);
			}
		}
		
	}
	
}
