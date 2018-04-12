package us.avansies.RuneCraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sndr, Command cmd, String label, String[] args) {
		YamlConfiguration conYAML = YamlConfiguration.loadConfiguration(RCFront.config);
		if(!(sndr instanceof Player)) {
			sndr.sendMessage("This command can not be executed by console personnel. Please try again in-game.");
			return true;
		}
		Player player = (Player) sndr;
		switch(cmd.getName().toLowerCase()) {
		case "rc":
			if(args.length < 1) {
				sndr.sendMessage(RCFront.adminPrefix + ChatColor.RED + " Invalid arguments. Please use /rc help for a list of commands.");
				return true;
			}
			switch(args[0].toLowerCase()) {
			case "selection":
				if(args.length < 2) {
					sndr.sendMessage(RCFront.adminPrefix + ChatColor.RED + " Invalid arguments. Please use /rc help for a list of commands.");
					return true;
				}
				switch(args[1].toLowerCase()) {
				case "info":
//					Display info
					int xCoord = conYAML.getInt("locations.selection.x");
					int zCoord = conYAML.getInt("locations.selection.z");
					player.sendMessage(RCFront.adminPrefix + ChatColor.DARK_AQUA + " Coords: " + xCoord + ", " + Bukkit.getServer().getWorld(conYAML.getString("locations.selection.world")).getHighestBlockYAt(xCoord, zCoord) + ", " + zCoord + ")");
					break;
				case "tp":
				case "teleport":
					double yCoord = Methods.teleportTo("selection", player);
					player.sendMessage(RCFront.adminPrefix + ChatColor.DARK_AQUA + " Teleported to selection location.");
					player.sendMessage(RCFront.adminPrefix + ChatColor.DARK_AQUA + " Coords: " + conYAML.getInt("locations.selection.x") + ", " + yCoord + ", " + conYAML.getInt("locations.selection.z") + ")");
					break;
				}
				break;
			}
			break;
		}
		return true;
	}
	
}
