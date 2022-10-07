package net.fortressgames.fortressbungeeessentials.commands.cmd;

import net.fortressgames.fortressbungeeessentials.Lang;
import net.fortressgames.fortressbungeeessentials.commands.CommandBase;
import net.fortressgames.fortressbungeeessentials.users.User;
import net.fortressgames.fortressbungeeessentials.users.UserModule;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MsgCommand extends CommandBase {

	public MsgCommand() {
		super("msg", null, "tell");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(sender instanceof ProxiedPlayer) {
			User user = UserModule.getInstance().getUser(((ProxiedPlayer) sender));

			if(args.length <= 1) {
				sender.sendMessage(Lang.MSG_USAGE);
				return;
			}

			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

			if(target == null) {
				sender.sendMessage(Lang.UNKNOWN_PLAYER);
				return;
			}

			StringBuilder message = new StringBuilder();
			for(int i = 1; i < args.length; ++i) {
				message.append(args[i]).append(" ");
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