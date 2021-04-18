package com.ageekhub.discord.notifier;

import org.bukkit.event.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import discord4j.core.DiscordClient;

/**
 * DiscordChannelNotifier
 * 
 * Main Listener
 * 
 * @author zildjian
 * @since 2021.04.17.
 * 
 */
public class DiscordChannelNotifier implements Listener {

	private DiscordChatSender sender;

	public DiscordChannelNotifier(DiscordClient client) {
		sender = new DiscordChatSender(client);
		sender.SendMessage(">>> :robot: **Bot Logged In** :robot:");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		sender.SendMessage(">>> :video_game: **" + event.getPlayer().getName() + "** has **joined** the game :video_game:");
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		sender.SendMessage(">>> **" + event.getPlayer().getName() + "** has **left** the game");
	}

	@EventHandler
	public void onPlayerDied(PlayerDeathEvent event) {
		sender.SendMessage(">>> :knife: " + event.getDeathMessage() + " :knife:");
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (!event.getMessage().startsWith("@discord ")) {
			return;
		}
		sender.SendMessage(">>> Message from **" + event.getPlayer().getName() + "**: \n```\n"
				+ event.getMessage().substring(9) + "\n```");
	}

	/*
	 * @EventHandler public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent
	 * event) { event.getAdvancement().getCriteria().forEach(advancement -> {
	 * sender.SendMessage("Congratulations to "+event.getPlayer().getName() +
	 * " achieved: " + advancement); }); }
	 */
}
