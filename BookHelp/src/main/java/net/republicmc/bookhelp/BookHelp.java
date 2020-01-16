package net.republicmc.bookhelp;

import org.bukkit.plugin.java.JavaPlugin;

public class BookHelp extends JavaPlugin {
	@Override
	public void onEnable() {
		Messages.get().load(this);
		BookConfig.get().load(this);
		
		CmdBookHelp cmdBookHelp = new CmdBookHelp();
		getCommand("bookhelp").setExecutor(cmdBookHelp);
		getCommand("bookhelp").setTabCompleter(cmdBookHelp);
		
		CmdBookHelpRegister cmdBookHelpRegister = new CmdBookHelpRegister();
		getCommand("bookhelpregister").setExecutor(cmdBookHelpRegister);
		getCommand("bookhelpregister").setTabCompleter(cmdBookHelpRegister);
	}
}
