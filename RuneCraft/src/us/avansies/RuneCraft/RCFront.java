package us.avansies.RuneCraft;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RCFront extends JavaPlugin {
	
	public static File plugDir;
	public static File config;
	
	public void onEnable() {
		plugDir = Bukkit.getServer().getPluginManager().getPlugin("RuneCraft").getDataFolder();
		if(!plugDir.exists()) {
			plugDir.mkdir();
		}
		config = new File(plugDir.getPath() + File.separator + "config.yml");
		if(!config.exists()) {
			try {
				config.createNewFile();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}
