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
			YamlConfiguration defYAML = YamlConfiguration.loadConfiguration(RCFront.defaults);
			if(!datYAML.contains("tag")) {
				long tag = createTag();
				while(alreadyExists(tag)) {
					tag = createTag();
				}
				datYAML.set("tag", tag);
			}
			if(!datYAML.contains("total")) {
				datYAML.set("total", defYAML.get("total"));
			}
			if(!datYAML.contains("combat")) {
				datYAML.set("combat", defYAML.get("comabt"));
			}
			if(!datYAML.contains("skills")) {
				datYAML.createSection("skills");
				datYAML.set("skills.attack", defYAML.get("skills.attack"));
				datYAML.set("skills.strength", defYAML.get("skills.strength"));
				datYAML.set("skills.defence", defYAML.get("skills.defence"));
				datYAML.set("skills.ranged", defYAML.get("skills.ranged"));
				datYAML.set("skills.prayer", defYAML.get("skills.prayer"));
				datYAML.set("skills.magic", defYAML.get("skills.magic"));
				datYAML.set("skills.constitution", defYAML.get("skills.constitution"));
				datYAML.set("skills.crafting", defYAML.get("skills.crafting"));
				datYAML.set("skills.mining", defYAML.get("skills.mining"));
				datYAML.set("skills.smithing", defYAML.get("skills.smithing"));
				datYAML.set("skills.fishing", defYAML.get("skills.fishing"));
				datYAML.set("skills.cooking", defYAML.get("skills.cooking"));
				datYAML.set("skills.firemaking", defYAML.get("skills.firemaking"));
				datYAML.set("skills.woodcutting", defYAML.get("skills.woodcutting"));
				datYAML.set("skills.runecrafting", defYAML.get("skills.runecrafting"));
				datYAML.set("skills.fletching", defYAML.get("skills.fletching"));
				datYAML.set("skills.agility", defYAML.get("skills.agility"));
				datYAML.set("skills.herblore", defYAML.get("skills.herblore"));
				datYAML.set("skills.theiving", defYAML.get("skills.theiving"));
				datYAML.set("skills.slayer", defYAML.get("skills.slayer"));
				datYAML.set("skills.farming", defYAML.get("skills.farming"));
				datYAML.set("skills.hunter", defYAML.get("skills.hunter"));
				datYAML.set("skills.summoning", defYAML.get("skills.summoning"));
			}
			try {
				datYAML.save(playerDat);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return playerDat;
		}
		return null;
	}
	
	public static void setIfNotExistant(File file, String key, Object value) {
		YamlConfiguration fileYAML = YamlConfiguration.loadConfiguration(file);
		if(!fileYAML.contains(key)) {
			fileYAML.set(key, value);
		}
		try {
			fileYAML.save(file);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void setUpDefaults(File defaults) {
		YamlConfiguration defYAML = YamlConfiguration.loadConfiguration(defaults);
		defYAML.set("total", 0);
		defYAML.set("combat", 3);
		defYAML.set("skills.attack", 0);
		defYAML.set("skills.strength", 0);
		defYAML.set("skills.defence", 0);
		defYAML.set("skills.ranged", 0);
		defYAML.set("skills.prayer", 0);
		defYAML.set("skills.magic", 0);
		defYAML.set("skills.constitution", 10);
		defYAML.set("skills.crafting", 0);
		defYAML.set("skills.mining", 0);
		defYAML.set("skills.smithing", 0);
		defYAML.set("skills.fishing", 0);
		defYAML.set("skills.cooking", 0);
		defYAML.set("skills.firemaking", 0);
		defYAML.set("skills.woodcutting", 0);
		defYAML.set("skills.runecrafting", 0);
		defYAML.set("skills.fletching", 0);
		defYAML.set("skills.agility", 0);
		defYAML.set("skills.herblore", 0);
		defYAML.set("skills.theiving", 0);
		defYAML.set("skills.slayer", 0);
		defYAML.set("skills.farming", 0);
		defYAML.set("skills.hunter", 0);
		defYAML.set("skills.summoning", 0);
		try {
			defYAML.save(defaults);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean alreadyExists(long tag) {
		for(File targDat : RCFront.plugDir.listFiles()) {
			if(YamlConfiguration.loadConfiguration(targDat).getLong("tag") == tag) {
				return true;
			}
		}
		return false;
	}
	
	public static int teleportTo(String location, Player player) {
		YamlConfiguration conYAML = YamlConfiguration.loadConfiguration(RCFront.config);
		Location teleLoc;
		switch(location) {
		case "selection":
			int xCoord = conYAML.getInt("locations.selection.x");
			int zCoord = conYAML.getInt("locations.selection.z");
			int yCoord = Bukkit.getServer().getWorld((String) conYAML.get("locations.selection.world")).getHighestBlockYAt(xCoord, zCoord);
			teleLoc = new Location(Bukkit.getServer().getWorld(conYAML.getString("locations.selection.world")), (double) conYAML.getInt("locations.selection.x"), yCoord, (double) conYAML.getInt("locations.selection.z"), (float) conYAML.getInt("locations.selection.pitch"), (float) conYAML.getInt("locations.selection.yaw"));
			player.teleport(teleLoc);
			return yCoord;
		}
		return 0;
	}
	
}
