package net.fortressgames.fortressbungeeessentials;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Announcer {

	private int count = 0;
	private final List<String> messages;

	/**
	 * Sends messages across the server about info and sales
	 */
	public Announcer() {
		messages = FortressBungeeEssentials.getInstance().getConfig().getConfig().getStringList("Announcer-Messages");
		trigger();
	}

	private void trigger() {
		FortressBungeeEssentials.getInstance().getProxy().getScheduler().schedule(FortressBungeeEssentials.getInstance(), () -> {

			for (ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {

				for (ProxiedPlayer pp : serverInfo.getPlayers()) {
					pp.sendMessage(TextComponent.fromLegacyText(" "));
					pp.sendMessage(TextComponent.fromLegacyText(ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', messages.get(count))));
					pp.sendMessage(TextComponent.fromLegacyText(" "));
				}
			}

			count++;

			if(messages.size() == count) {
				count = 1;
			}

			trigger();
		}, 20, TimeUnit.MINUTES);
	}
}