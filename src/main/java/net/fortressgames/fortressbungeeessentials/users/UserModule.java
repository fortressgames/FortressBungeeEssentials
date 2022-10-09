package net.fortressgames.fortressbungeeessentials.users;

import net.fortressgames.fortressbungeeessentials.FortressBungeeEssentials;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;

public class UserModule implements Listener {

	private static UserModule instance;
	private final HashMap<ProxiedPlayer, User> users = new HashMap<>();

	public static UserModule getInstance() {
		if(instance == null) {
			instance = new UserModule();
		}

		return instance;
	}

	public User getUser(ProxiedPlayer player) {
		return this.users.get(player);
	}

	@EventHandler
	public void join(PostLoginEvent e) {
		ProxiedPlayer player = e.getPlayer();

		this.users.put(player, new User());

		ProxyServer.getInstance().getPlayers().forEach(pp -> pp.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
				FortressBungeeEssentials.getInstance().getConfig().getConfig().getString("Join-Message").replace("%player%", player.getDisplayName())
		))));
	}

	@EventHandler
	public void quit(PlayerDisconnectEvent e) {
		ProxiedPlayer player = e.getPlayer();

		this.users.remove(player);
		ProxyServer.getInstance().getPlayers().forEach(pp -> pp.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
				FortressBungeeEssentials.getInstance().getConfig().getConfig().getString("Quit-Message").replace("%player%", player.getDisplayName())
		))));
	}
}