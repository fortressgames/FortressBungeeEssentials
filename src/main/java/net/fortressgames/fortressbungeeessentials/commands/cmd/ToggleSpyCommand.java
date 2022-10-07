package net.fortressgames.fortressbungeeessentials.commands.cmd;

import net.fortressgames.fortressbungeeessentials.Lang;
import net.fortressgames.fortressbungeeessentials.PermissionLang;
import net.fortressgames.fortressbungeeessentials.commands.CommandBase;
import net.fortressgames.fortressbungeeessentials.users.User;
import net.fortressgames.fortressbungeeessentials.users.UserModule;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ToggleSpyCommand extends CommandBase {

	public ToggleSpyCommand() {
		super("togglespy", PermissionLang.TOGGLE_SPY);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(sender instanceof ProxiedPlayer player) {
			User user = UserModule.getInstance().getUser(player);

			if(user.isMsgSpy()) {
				user.setMsgSpy(false);
				player.sendMessage(Lang.spyToggle(false));

			} else {
				user.setMsgSpy(true);
				player.sendMessage(Lang.spyToggle(true));
			}
		}
	}
}