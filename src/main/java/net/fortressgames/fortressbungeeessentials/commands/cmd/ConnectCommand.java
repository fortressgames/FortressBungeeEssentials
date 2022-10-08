package net.fortressgames.fortressbungeeessentials.commands.cmd;

import net.fortressgames.fortressbungeeessentials.Lang;
import net.fortressgames.fortressbungeeessentials.PermissionLang;
import net.fortressgames.fortressbungeeessentials.commands.CommandBase;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class ConnectCommand extends CommandBase {

	public ConnectCommand() {
		super("connect", PermissionLang.CONNECT);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(args.length == 0) {
			sender.sendMessage(Lang.CONNECT_USAGE);
			return;
		}

		if(args.length == 2) {
			// Target player
			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

			if(target == null) {
				sender.sendMessage(Lang.UNKNOWN_PLAYER);
				return;
			}

			ServerInfo targetServer = null;
			for(ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {
				if(serverInfo.getName().equalsIgnoreCase(args[1])) {
					targetServer = serverInfo;
					break;
				}
			}

			if(targetServer != null) {

				if(target.getServer().getInfo().equals(targetServer)) {
					sender.sendMessage(Lang.TARGET_ALREADY_CONNECTED);
					return;
				}

				sender.sendMessage(Lang.connectSending(target.getName(), targetServer.getName()));
				target.sendMessage(Lang.connectSent(targetServer.getName()));
				target.connect(targetServer);

			} else {
				sender.sendMessage(Lang.UNKNOWN_SERVER);
			}
		}
	}

	@Override
	public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
		if(args.length == 1) {
			List<String> list = new ArrayList<>();
			for(ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()) {
				list.add(pp.getName());
			}

			return list;
		}

		if(args.length == 2) {
			List<String> list = new ArrayList<>();
			for(ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {
				list.add(serverInfo.getName());
			}

			return list;
		}

		return super.onTabComplete(sender, args);
	}
}