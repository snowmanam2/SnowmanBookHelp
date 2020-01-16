package net.republicmc.bookhelp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdBookHelp implements CommandExecutor, TabCompleter {

	private void printUsage(CommandSender sender, String label) {
		sender.sendMessage(Messages.get("command.bookhelp.usage", label));
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.get("command.bookhelp.playerOnly"));
			return true;
		}
		
		Player p = (Player) sender;
		
		if (args.length < 1) {
			printUsage(sender, label);
			return true;
		}
		
		ItemStack book = BookConfig.get().getBook(args[0]);
		
		if (book == null) {
			sender.sendMessage(Messages.get("command.bookhelp.failureInvalid", args[0]));
			return true;
		}
		
		BookUtil.showBook(book, p);

		return true;
	}

	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> retval = new ArrayList<String>();
		
		if (args.length == 0) {
			retval.addAll(BookConfig.get().getBookKeys());
		} else if (args.length == 1) {
			for (String key : BookConfig.get().getBookKeys()) {
				if (key.startsWith(args[0])) {
					retval.add(key);
				}
			}
		}
		
		return retval;
	}
	
}
