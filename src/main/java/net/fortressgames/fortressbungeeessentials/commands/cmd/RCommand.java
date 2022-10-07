package net.fortressgames.fortressbungeeessentials.commands.cmd;

import net.fortressgames.fortressbungeeessentials.Lang;
import net.fortressgames.fortressbungeeessentials.commands.CommandBase;
import net.fortressgames.fortressbungeeessentials.users.User;
import net.fortressgames.fortressbungeeessentials.users.UserModule;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class RCommand extends CommandBase {

	public RCommand() {
		super("r", null);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(sender instanceof ProxiedPlayer) {

			User user = UserModule.getInstance().getUser((ProxiedPlayer) sender);

			if(user.getTargetMsg() == null) {
				sender.sendMessage(Lang.R_TARGET_UNKNOWN);
				return;
			}

			if(args.length == 0) {
				sender.sendMessage(Lang.R_USAGE);
				return;
			}

			ProxiedPlayer target = user.getTargetMsg();

			if(target == null) {
				sender.sendMessage(Lang.UNKNOWN_PLAYER);
				return;
			}

			StringBuilder message = new StringBuilder();
			for(String arg : args) {
				message.append(arg).append(" ");
			}

			sender.sendMessage(Lang.msgSender(target.getName(), message.toString()));
			target.sendMessage(Lang.msgTarget(sender.getName(), message.toString()));

			user.setTargetMsg(target);

			for(ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()) {

				if(pp.equals(sender) || pp.equals(target)) continue;

				if(UserModule.getInstance().getUser(pp).isMsgSpy()) {
					pp.sendMessage(Lang.msgSpy(sender.getName(), target.getName(), message.toString()));
				}
			}
		}
	}
}