package us.avansies.RuneCraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sndr, Command cmd, String label, String[] args) {
		if(!(sndr instanceof Player)) {
			sndr.sendMessage("This command can not be executed by console personnel. Please try again in-game.");
			return true;
		}
		switch(cmd.getName().toLowerCase()) {
		case "rc":
			if(args.length < 1) {
				sndr.sendMessage(RCFront.prefix + ChatColor.RED + " Invalid arguments. Please use /rc help for a list of commands.");
				return true;
			}
			switch(args[0].toLowerCase()) {
			case "selection":
				if(args.length < 2) {
					sndr.sendMessage(RCFront.prefix + ChatColor.RED + " Invalid arguments. Please use /rc help for a list of commands.");
					return true;
				}
				switch(args[1].toLowerCase()) {
				case "info":
//					Display info
					break;
				case "tp":
				case "teleport":
//					Teleport player
					break;
				}
				break;
			}
			break;
		}
		return true;
	}
	
}
