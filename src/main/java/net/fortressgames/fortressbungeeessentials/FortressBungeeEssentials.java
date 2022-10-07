package net.fortressgames.fortressbungeeessentials;

import lombok.Getter;
import net.fortressgames.fortressbungeeessentials.commands.cmd.*;
import net.fortressgames.fortressbungeeessentials.users.UserModule;
import net.fortressgames.fortressbungeeessentials.utils.ConsoleMessage;
import net.md_5.bungee.api.plugin.Plugin;

public class FortressBungeeEssentials extends Plugin {

	@Getter private static FortressBungeeEssentials instance;

	/**
	 * Called when the plugin is first loaded by Spigot
	 */
	@Override
	public void onEnable() {
		instance = this;

		// Listeners
		getProxy().getPluginManager().registerListener(this, UserModule.getInstance());

		// Commands
		getProxy().getPluginManager().registerCommand(this, new ListCommand());
		getProxy().getPluginManager().registerCommand(this, new FindCommand());
		getProxy().getPluginManager().registerCommand(this, new BroadcastCommand());
		getProxy().getPluginManager().registerCommand(this, new ServerCommand());
		getProxy().getPluginManager().registerCommand(this, new ConnectCommand());
		getProxy().getPluginManager().registerCommand(this, new MsgCommand());
		getProxy().getPluginManager().registerCommand(this, new RCommand());
		getProxy().getPluginManager().registerCommand(this, new ToggleSpyCommand());

		getLogger().info(ConsoleMessage.GREEN + "Version: " + getDescription().getVersion() + " Enabled!" + ConsoleMessage.RESET);
	}

	/**
	 * Called when the server is restarted or stopped
	 */
	@Override
	public void onDisable() {
		getLogger().info(ConsoleMessage.RED + "Version: " + getDescription().getVersion() + " Disabled!" + ConsoleMessage.RESET);
	}
}

//TODO
// join / quit message
// ping listener