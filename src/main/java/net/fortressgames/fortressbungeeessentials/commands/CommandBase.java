package net.fortressgames.fortressbungeeessentials.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;

public abstract class CommandBase extends Command implements TabExecutor {

	public CommandBase(String cmd, String permission, String... aliases) {
		super(cmd, permission, aliases);
	}

	public CommandBase(String cmd) {
		super(cmd);
	}

	@Override
	public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}
}