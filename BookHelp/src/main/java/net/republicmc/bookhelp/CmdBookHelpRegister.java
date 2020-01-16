package net.republicmc.bookhelp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdBookHelpRegister implements CommandExecutor, TabCompleter {

	private void printUsage(CommandSender sender, String label) {
		sender.sendMessage(Messages.get("command.register.usage", label));
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.get("command.register.playerOnly"));
			return true;
		}
		
		Player p = (Player)sender;
		
		if (args.length < 1) {
			printUsage(sender, label);
			return true;
		}
		
		String key = args[0];
		
		@SuppressWarnings("deprecation")
		ItemStack item = p.getItemInHand();
		
		if (!item.getType().equals(Material.WRITTEN_BOOK)) {
			sender.sendMessage(Messages.get("command.register.failureInvalid"));
			return true;
		}
		
		BookConfig.get().setBook(key, item);
		
		return true;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}

}
