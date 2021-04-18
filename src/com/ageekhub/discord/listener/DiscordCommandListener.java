package com.ageekhub.discord.listener;

import org.bukkit.plugin.java.JavaPlugin;

import com.ageekhub.discord.Configuration;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

/**
 * DiscordCommandListener
 * 
 * Listens to commands from the discord server and executes them (for now this
 * only sends a message from the discord server to the minecraft server)
 * 
 * @author zildjian
 * @since 2021.04.18.
 */
public class DiscordCommandListener {

	private JavaPlugin plugin;
	private String clientSelfID = null;

	public DiscordCommandListener(JavaPlugin plugin) {
		this.plugin = plugin;
	};

	public void subscribe(GatewayDiscordClient client) {
		client.on(MessageCreateEvent.class).subscribe(event -> doMessageCreateEvent(event, client));
	}

	private void doMessageCreateEvent(MessageCreateEvent event, GatewayDiscordClient client) {
		if (clientSelfID != null) {
			broadcastToServerIfMentioned(event);
			return;
		}
		client.getSelf().subscribe(self -> {
			// cache clientSelfID
			clientSelfID = self.getId().asString();
			broadcastToServerIfMentioned(event);
		});
	}

	private void broadcastToServerIfMentioned(MessageCreateEvent event) {

		Message message = event.getMessage();
		if (Configuration.getInstance().getDebug()) {
			System.out.println(message.getContent());
		}

		String mention = "<@!" + clientSelfID + ">";
		if (message.getContent().startsWith(mention)) {
			plugin.getServer().broadcastMessage("discord[" + event.getMember().get().getUsername() + "]: "
					+ message.getContent().substring(mention.length()).trim());
		}
	}
}
