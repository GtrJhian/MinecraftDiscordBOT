package com.ageekhub.discord;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {
	
	private Configuration() {};
	
	private DiscordBot discordBotConfig = null;
	private List<String> ops =  null;
	private boolean debug = false;
	
	private static Configuration instance = null;
	
	public static void ManageConfiguration(FileConfiguration config) {
		instance = new Configuration();
		
		/**
		 * debug: false
		 */
		
		instance.debug = config.getBoolean("debug");
		/**
		 * ops:
		 * - Spark014
		 * - zljian13
		 * 
		 */
		instance.ops = config.getStringList("ops");
		
		/**
		 * discord:
		 *   token: foo
		 * 	 channels:
		 * 	 - channel1
		 * 	 - channel2
		 */
		ConfigurationSection discord = config.getConfigurationSection("discord");
		instance.discordBotConfig = new DiscordBot(discord.getString("token"), discord.getStringList("channels"));
		
		config.options().copyDefaults(true);
	}
	
	public static Configuration getInstance() {
		if (instance == null) {
			System.err.println("Configuration.ManageConfiguration should be called first, please check your code");
			return null;
		}
		return instance;
	}
	
	
	public DiscordBot getDiscord() {
		return discordBotConfig;
	}
	
	public List<String> getOPs() {
		return ops;
	}
	
	public boolean getDebug() {
		return debug;
	}
	
	
	public static class DiscordBot {
		private String token;
		private List<String> channels;
		
		public DiscordBot(String token, List<String> channels) {
			this.token = token;
			this.channels = channels;
		}
		
		public String getToken() {
			return token;
		}
		
		public List<String> getChannels() {
			return channels;
		}
	}
	
}
