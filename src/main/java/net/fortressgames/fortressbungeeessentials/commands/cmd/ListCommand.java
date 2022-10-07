package net.fortressgames.fortressbungeeessentials.commands.cmd;

import net.fortressgames.fortressbungeeessentials.Lang;
import net.fortressgames.fortressbungeeessentials.PermissionLang;
import net.fortressgames.fortressbungeeessentials.commands.CommandBase;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;

public class ListCommand extends CommandBase {

	public ListCommand() {
		super("list", PermissionLang.LIST);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		sender.sendMessage(Lang.LINE);
		sender.sendMessage(TextComponent.fromLegacyText(ChatColor.GOLD + ChatColor.BOLD.toString() + "Servers:"));

		for(ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {

			sender.sendMessage(TextComponent.fromLegacyText(ChatColor.YELLOW + serverInfo.getName().toUpperCase() +
					ChatColor.GRAY + " (" + ChatColor.DARK_AQUA + serverInfo.getPlayers().size() + ChatColor.GRAY + ")"));

			sender.sendMessage(TextComponent.fromLegacyText(serverInfo.getPlayers().toString()
					.replace(",", ChatColor.BLUE + "," + ChatColor.WHITE)
					.replace("[", ChatColor.GRAY + "[" + ChatColor.WHITE)
					.replace("]", ChatColor.GRAY + "]" + ChatColor.WHITE)
			));
		}

		sender.sendMessage(Lang.LINE);
	}
}