package de.karlthebee.spigot.id;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
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
		Set<DetailType> types = new HashSet<>();
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("help")) {
				p.sendMessage(ChatColor.YELLOW + "--- HELP ---");
				p.sendMessage("/id [args] : Gives you item and block infomration");
				p.sendMessage(
						"'args' is an list of characters (p.ex '/id bl' from the following, '/id all' shows every parameter");

				for (DetailType type : DetailType.values()) {
					p.sendMessage("  ->  " + ChatColor.YELLOW + type.getShortValue() + ChatColor.WHITE + "   "
							+ fillString(type.name().toLowerCase(), 7) + "   " + ChatColor.GRAY
							+ type.getDescription());
				}
				return true;
			}
			types = DetailType.fromShorts(args[0]);
		}
		
		Inputs input =null;
		String cmdName = command.getName().toLowerCase();
		if (cmdName.equals("idoff")){
			input=Inputs.OFF_HAND;
		}else if (cmdName.equals("idblock")){
			input=Inputs.BLOCK;
		}else if (cmdName.equals("id")){
			input=Inputs.MAIN_HAND;
		}
		
		IDPrinter.printID(p, types, input);
		return true;
	}

	private String fillString(String s, int fill) {
		while (s.length() < fill) {
			s = s + " ";
		}

		while (s.length() > fill) {
			s = s.trim();
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

}
