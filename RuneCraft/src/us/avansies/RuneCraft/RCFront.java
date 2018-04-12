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
	public static File defaults;
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
//			Create a YamlConfiguration object out of the config file
			YamlConfiguration conYAML = YamlConfiguration.loadConfiguration(config);
//			Create locations section in config YAML
			conYAML.createSection("locations");
//			Create selection section in locations section
			conYAML.createSection("locations.selection");
//			Set the value of "world" in the selection section to "world"
			conYAML.set("locations.selection.world", "world");
//			Set the value of "x" to 0
			conYAML.set("locations.selection.x", 0);
//			Set the value of "y" to the y coordinate of the highest block at the coordinates of (0, 0) in the world "world"
			conYAML.set("locations.selection.y", Bukkit.getServer().getWorld("world").getHighestBlockYAt(0, 0));
//			Set the value of "z" to 0
			conYAML.set("locations.selection.z", 0);
//			Set the value of "pitch" to 180
			conYAML.set("locations.selection.pitch", 180);
//			Set the value of "yaw" to 0
			conYAML.set("locations.selection.yaw", 0);
			try {
//				Save the configuration yaml into config.yml, via the config File object
				conYAML.save(config);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		defaults = new File(plugDir.getPath() + File.separator + "defaults.yml");
		if(!defaults.exists()) {
			try {
				defaults.createNewFile();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			Methods.setUpDefaults(defaults);
		}
//		Set the value of playersFold to a folder within the plugin directory by the name of "players"
		playersFold = new File(plugDir.getPath() + File.separator + "players");
//		Check to see if the players folder exists
		if(!playersFold.exists()) {
//			Create a directory out of the players file
			playersFold.mkdir();
		}
//		Loop through each online player
		for(Player targPlayer : Bukkit.getServer().getOnlinePlayers()) {
//			Check to see if the target player of the loop has a matching data file
			if(!Methods.hasDataFile(targPlayer)) {
//				Create a matching data file for the target player
				Methods.createPlayerFile(targPlayer);
//				Teleport the player to the "selection" location specified in config.yml
				Methods.teleportTo("selection", targPlayer);
			}
		}
//		Register the Events class as an event listener for this plugin
		Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
//		Set executor of the "/rc" command to an instance of the Commands class
		Bukkit.getServer().getPluginCommand("rc").setExecutor(new Commands());
	}
	
}
