package com.ageekhub.discord;

import org.bukkit.plugin.java.JavaPlugin;

import com.ageekhub.discord.listener.DiscordCommandListener;
import com.ageekhub.discord.notifier.DiscordChannelNotifier;
import discord4j.core.DiscordClient;


/**
 * Main
 * 
 * Minecraft plugin main entrypoint Register Events
 * 
 * @author zildjian
 * @since 2021.04.17.
 */
public class Main extends JavaPlugin {

	@Override
	public void onLoad() {
		super.onLoad();
		Configuration.ManageConfiguration(getConfig());
		saveConfig();
	}

	@Override
	public void onEnable() {
		System.out.println("Enabling DiscordNotifier Plugin");
		super.onEnable();
		DiscordClient.create(Configuration.getInstance().getDiscord().getToken()).login().subscribe(client -> {
			System.out.println("Client Logged In");
			getServer().getPluginManager().registerEvents(new DiscordChannelNotifier(client.rest()), this);
			new DiscordCommandListener(this).subscribe(client);
		});

	}

}
