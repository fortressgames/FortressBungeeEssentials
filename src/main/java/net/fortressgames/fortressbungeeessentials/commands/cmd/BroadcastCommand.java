package net.fortressgames.fortressbungeeessentials.commands.cmd;

import net.fortressgames.fortressbungeeessentials.Lang;
import net.fortressgames.fortressbungeeessentials.PermissionLang;
import net.fortressgames.fortressbungeeessentials.commands.CommandBase;
import net.fortressgames.fortressbungeeessentials.utils.ConsoleMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BroadcastCommand extends CommandBase {

	public BroadcastCommand() {
		super("broadcast", PermissionLang.ALERT, "alert");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(args.length == 0) {
			sender.sendMessage(Lang.BROADCAST_USAGES);
			return;
		}

		StringBuilder message = new StringBuilder();
		for(String arg : args) {
			message.append(arg).append(" ");
		}

		// Print to console
		System.out.println(ConsoleMessage.RED + "[ALERT] " +
				ConsoleMessage.WHITE + ChatColor.translateAlternateColorCodes('&', message.toString()) + ConsoleMessage.RESET);
		System.out.println(ConsoleMessage.YELLOW + "Alert sent by " + sender.getName() + ConsoleMessage.RESET);

		// Send message to all players
		for(ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()) {
			pp.sendMessage(Lang.broadcastMessage(message.toString()));
		}
	}
}