package us.avansies.RuneCraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
	
	@EventHandler
	public void onAsyncPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
//		boolean hasDataFile = false;
//		for(File targData : RCFront.playersFold.listFiles()) {
//			System.out.println(targData.getName().substring(0, targData.getName().length() - 4));
//			if(targData.getName().substring(0, targData.getName().length() - 4).equalsIgnoreCase(player.getUniqueId().toString())) {
//				hasDataFile = true;
//			}
//		}
//		if(!hasDataFile) {
//		}
		Methods.createPlayerFile(player);
	}
	
}
