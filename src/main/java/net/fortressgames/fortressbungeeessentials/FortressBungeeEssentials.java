package net.fortressgames.fortressbungeeessentials;

import lombok.Getter;
import net.fortressgames.fortressbungeeessentials.commands.cmd.*;
import net.fortressgames.fortressbungeeessentials.listeners.ProxyPingListener;
import net.fortressgames.fortressbungeeessentials.users.UserModule;
import net.fortressgames.fortressbungeeessentials.utils.Config;
import net.fortressgames.fortressbungeeessentials.utils.ConsoleMessage;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;

public class FortressBungeeEssentials extends Plugin {

	@Getter private static FortressBungeeEssentials instance;
	@Getter private Config config;

	/**
	 * Called when plugin first loads by spigot and is called before onEnable
	 */
	@Override
	public void onLoad() {
		// Create Default folder
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
	}

	/**
	 * Called when the plugin is first loaded by Spigot
	 */
	@Override
	public void onEnable() {
		instance = this;

		File configFile = new File(getDataFolder() + "/config.yml");
		Config config = new Config(configFile);
		this.config = config;

		if(!config.getConfig().contains("Join-Message")) {
			config.getConfig().set("Join-Message", "&a%player% joined the network");
			config.saveConfig();
		}
		if(!config.getConfig().contains("Quit-Message")) {
			config.getConfig().set("Quit-Message", "&a%player% quit the network");
			config.saveConfig();
		}
		if(!config.getConfig().contains("Ping-Message")) {
			config.getConfig().set("Ping-Message", "&7A minecraft server");
			config.saveConfig();
		}
		if(!config.getConfig().contains("Announcer-Messages")) {
			config.getConfig().set("Announcer-Messages", new ArrayList<>());
			config.saveConfig();
		}

		new Announcer();

		// Listeners
		getProxy().getPluginManager().registerListener(this, UserModule.getInstance());
		getProxy().getPluginManager().registerListener(this, new ProxyPingListener());

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