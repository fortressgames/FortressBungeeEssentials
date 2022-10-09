package net.fortressgames.fortressbungeeessentials.utils;

import lombok.Getter;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

	@Getter private Configuration config;
	private ConfigurationProvider configurationProvider;

	private final File file;

	public Config(File file) {
		this.file = file;
		loadConfig();
	}

	public void loadConfig() {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		configurationProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);
		try {
			config = configurationProvider.load(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void saveConfig() {
		try {
			configurationProvider.save(config, file);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}