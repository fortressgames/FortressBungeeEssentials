package net.fortressgames.fortressbungeeessentials.commands.cmd;

import net.fortressgames.fortressbungeeessentials.Lang;
import net.fortressgames.fortressbungeeessentials.PermissionLang;
import net.fortressgames.fortressbungeeessentials.commands.CommandBase;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends CommandBase {

	public FindCommand() {
		super("find", PermissionLang.FIND);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(args.length == 0) {
			sender.sendMessage(Lang.FIND_USAGE);
			return;
		}

		ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

		if(target == null) {
			sender.sendMessage(Lang.UNKNOWN_PLAYER);
			return;
		}

		sender.sendMessage(Lang.foundPlayer(target.getName(), target.getServer().getInfo().getName().toUpperCase()));
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

		return super.onTabComplete(sender, args);
	}
}