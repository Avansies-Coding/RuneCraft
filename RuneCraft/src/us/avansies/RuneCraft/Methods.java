package us.avansies.RuneCraft;

import java.io.File;
import java.io.IOException;

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
	
	public static boolean createPlayerFile(Player player) {
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
			return false;
		}
		return true;
	}
	
	public static boolean alreadyExists(long tag) {
		for(File targDat : RCFront.plugDir.listFiles()) {
			if(YamlConfiguration.loadConfiguration(targDat).getLong("tag") == tag) {
				return true;
			}
		}
		return false;
	}
	
}
