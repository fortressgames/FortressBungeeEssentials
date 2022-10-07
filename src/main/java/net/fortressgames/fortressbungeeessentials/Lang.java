package net.fortressgames.fortressbungeeessentials;

import net.fortressgames.fortressbungeeessentials.utils.HexColor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class Lang {

	public static final String RED = HexColor.valueOf("#F00000") + "❙ ";
	public static final String YELLOW = HexColor.valueOf("#E8F000") + "❙ ";
	public static final String GREEN = HexColor.valueOf("#30F000") + "❙ ";
	public static final String BLUE = HexColor.valueOf("#0050F0") + "❙ ";
	public static final String PINK = HexColor.valueOf("#E800F0") + "❙ ";

	public static final BaseComponent[] MSG_USAGE = TextComponent.fromLegacyText(BLUE + "Usage: /msg <player> <message>");
	public static final BaseComponent[] R_USAGE = TextComponent.fromLegacyText(BLUE + "Usage: /r <message>");
	public static final BaseComponent[] CONNECT_USAGE = TextComponent.fromLegacyText(BLUE + "Usage: /send <player> <server>");
	public static final BaseComponent[] BROADCAST_USAGES = TextComponent.fromLegacyText(BLUE + "Usage: /broadcast <message>");
	public static final BaseComponent[] FIND_USAGE = TextComponent.fromLegacyText(BLUE + "Usage: /find <player>");

	public static final BaseComponent[] LINE = TextComponent.fromLegacyText(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "                                                                     ");
	public static final BaseComponent[] UNKNOWN_PLAYER = TextComponent.fromLegacyText(RED + "Cannot find that player on the network!");
	public static final BaseComponent[] UNKNOWN_SERVER = TextComponent.fromLegacyText(RED + "Cannot find that server!");

	public static final BaseComponent[] R_TARGET_UNKNOWN = TextComponent.fromLegacyText(ChatColor.RED + "Player not found! use /msg");

	public static BaseComponent[] msgSender(String targetName, String message) {
		return TextComponent.fromLegacyText(ChatColor.AQUA + ChatColor.BOLD.toString() + "You " + ChatColor.WHITE + "-> " + ChatColor.GREEN + targetName
				+ ChatColor.GRAY + ": " + message);
	}
	public static BaseComponent[] msgTarget(String senderName, String message) {
		return TextComponent.fromLegacyText(ChatColor.GREEN + senderName + ChatColor.WHITE + "-> " + ChatColor.AQUA + ChatColor.BOLD + "You" + ChatColor.GRAY + ": " + message);
	}
	public static BaseComponent[] msgSpy(String senderName, String targetName, String message) {
		return TextComponent.fromLegacyText(ChatColor.RED + ChatColor.BOLD.toString() + "[Spy] " + ChatColor.AQUA + senderName
				+ ChatColor.WHITE + " -> " + ChatColor.GREEN + targetName + ChatColor.GRAY + ": " + message);
	}
	public static BaseComponent[] spyToggle(boolean value) {
		if(value) {
			return TextComponent.fromLegacyText(GREEN + "Spy toggled on");
		}

		return TextComponent.fromLegacyText(GREEN + "Spy toggled off");
	}

	public static BaseComponent[] broadcastMessage(String message) {
		return TextComponent.fromLegacyText(ChatColor.RED + "[ALERT] " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', message));
	}

	public static BaseComponent[] connectSent(String serverName) {
		return TextComponent.fromLegacyText(GREEN + "You have been sent to " + serverName);
	}
	public static BaseComponent[] connectSending(String targetName, String serverName) {
		return TextComponent.fromLegacyText(GREEN + "Sending " + targetName + " to " + serverName);
	}

	public static BaseComponent[] foundPlayer(String targetName, String serverName) {
		return TextComponent.fromLegacyText(GREEN + targetName + " is on server: " + ChatColor.WHITE + serverName);
	}

	public static BaseComponent[] sending(String serverName) {
		return TextComponent.fromLegacyText(GREEN + "Sending you to " + serverName);
	}
}