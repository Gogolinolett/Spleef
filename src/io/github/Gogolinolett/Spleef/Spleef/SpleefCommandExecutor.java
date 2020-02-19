package io.github.Gogolinolett.Spleef.Spleef;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpleefCommandExecutor implements CommandExecutor{
	
	
	private static final String prefix = "Spleef";


	public String PermissionDenied(String[] permissions) {
		String output = prefix + "§4§lYou do not have permission to run this command.\n" + prefix
				+ "Permissions that grant access to this command:\n";
		for (String permission : permissions) {
			output += prefix + " - " + permission + "\n";
		}
		return output;
	}
	
	
	public boolean hasPermission(CommandSender sender, String[] permissions) {
		if (sender instanceof Server) {
			return true;
		}
		if (sender.isOp()) {
			if (sender instanceof Player) {

				return true;
			}

		}
		for (String permission : permissions) {
			if (sender.hasPermission(permission)) {
				return true;
			}
		}
		sender.sendMessage(PermissionDenied(permissions));
		return false;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		
		return false;
	}

}
