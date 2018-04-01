package us.avansies.RuneCraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
	
	@EventHandler
	public void onAsyncPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(Methods.hasDataFile(player)) {
			Methods.createPlayerFile(player);
		}
	}
	
}
