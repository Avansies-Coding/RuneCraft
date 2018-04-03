package us.avansies.RuneCraft;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Methods {
	
	public static long createTag() {
		String tag = "";
		for(int i = 0; i < 10; i++) {
			long rand = (long) (Math.floor(Math.random() * 9 + 1));
			tag = tag + rand;
		}
		return Long.parseLong(tag);
	}
	
	public static boolean hasDataFile(Player player) {
		for(File targFile : RCFront.playersFold.listFiles()) {
			if(targFile.getName().substring(0, targFile.getName().length() - 4).equalsIgnoreCase(player.getUniqueId().toString())) {
				return true;
			}
		}
		return false;
	}
	
	public static File createPlayerFile(Player player) {
		String playerUUID = player.getUniqueId().toString();
		File playerDat = new File(RCFront.playersFold + File.separator + playerUUID + ".yml");
		if(!playerDat.exists()) {
			try {
				playerDat.createNewFile();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			YamlConfiguration datYAML = YamlConfiguration.loadConfiguration(playerDat);
			long tag = createTag();
			while(alreadyExists(tag)) {
				tag = createTag();
			}
			datYAML.set("tag", tag);
			try {
				datYAML.save(playerDat);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return playerDat;
		}
		return null;
	}
	
	public static boolean alreadyExists(long tag) {
		for(File targDat : RCFront.plugDir.listFiles()) {
			if(YamlConfiguration.loadConfiguration(targDat).getLong("tag") == tag) {
				return true;
			}
		}
		return false;
	}
	
	public static double teleportTo(String location, Player player) {
		YamlConfiguration conYAML = YamlConfiguration.loadConfiguration(RCFront.config);
		Location teleLoc;
		switch(location) {
		case "selection":
			int xCoord = conYAML.getInt("locations.selection.x");
			int zCoord = conYAML.getInt("locations.selection.z");
			double yCoord = (double) Bukkit.getServer().getWorld((String) conYAML.get("locations.selection.world")).getHighestBlockYAt(xCoord, zCoord);
			teleLoc = new Location(Bukkit.getServer().getWorld(conYAML.getString("locations.selection.world")), (double) conYAML.getInt("locations.selection.x"), yCoord, (double) conYAML.getInt("locations.selection.z"), (float) conYAML.getInt("locations.selection.pitch"), (float) conYAML.getInt("locations.selection.yaw"));
			player.teleport(teleLoc);
			return yCoord;
		}
		return 0;
	}
	
}
