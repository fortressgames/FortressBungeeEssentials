package net.fortressgames.fortressbungeeessentials.commands.cmd;

import net.fortressgames.fortressbungeeessentials.Lang;
import net.fortressgames.fortressbungeeessentials.PermissionLang;
import net.fortressgames.fortressbungeeessentials.commands.CommandBase;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class ServerCommand extends CommandBase {

	public ServerCommand() {
		super("server", PermissionLang.SERVER, "servers");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(args.length == 0) {
			sender.sendMessage(Lang.LINE);
			sender.sendMessage(TextComponent.fromLegacyText(ChatColor.GOLD + ChatColor.BOLD.toString() + "Servers:"));

			for(ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {
				sender.sendMessage(TextComponent.fromLegacyText(ChatColor.YELLOW + serverInfo.getName().toUpperCase()));
			}

			sender.sendMessage(Lang.LINE);
			return;
		}

		if(sender instanceof ProxiedPlayer player) {

			for(ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {
				if(args[0].equalsIgnoreCase(serverInfo.getName())) {

					if(player.getServer().getInfo().equals(serverInfo)) {
						player.sendMessage(Lang.ALREADY_CONNECTED);
						return;
					}

					player.sendMessage(Lang.sending(serverInfo.getName()));
					player.connect(serverInfo);
					return;
				}
			}
		}
	}

	@Override
	public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
		if(args.length == 1) {
			List<String> list = new ArrayList<>();
			for(ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {
				list.add(serverInfo.getName().toUpperCase());
			}

			return list;
		}

		return super.onTabComplete(sender, args);
	}
}