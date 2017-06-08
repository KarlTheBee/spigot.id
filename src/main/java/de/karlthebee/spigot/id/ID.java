package de.karlthebee.spigot.id;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ID extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You cannot send the command from console");
			return true;
		}
		Player p = (Player) sender;
		boolean detail = command.getName().equalsIgnoreCase("iddetail");
		Player receiver = (args.length == 0) ? p
				: ((Bukkit.getPlayer(args[0]) != null) ? Bukkit.getPlayer(args[0]) : p);
		IDPrinter.printID(p, receiver, detail);
		return true;
	}

}
