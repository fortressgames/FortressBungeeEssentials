package net.fortressgames.fortressbungeeessentials.listeners;

import net.fortressgames.fortressbungeeessentials.FortressBungeeEssentials;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPingListener implements Listener {

	@EventHandler
	public void ping(ProxyPingEvent e) {
		ServerPing ping = e.getResponse();

		ping.setDescription(ChatColor.translateAlternateColorCodes('&',
				FortressBungeeEssentials.getInstance().getConfig().getConfig().getString("Ping-Message")));
		e.setResponse(ping);
	}
}