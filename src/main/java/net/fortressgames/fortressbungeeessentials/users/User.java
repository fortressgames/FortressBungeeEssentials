package net.fortressgames.fortressbungeeessentials.users;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class User {

	@Setter @Getter private ProxiedPlayer targetMsg;
	@Setter @Getter private boolean msgSpy;
}