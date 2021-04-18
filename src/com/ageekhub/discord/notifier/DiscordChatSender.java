package com.ageekhub.discord.notifier;

import java.util.ArrayList;
import java.util.List;

import com.ageekhub.discord.Configuration;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.discordjson.json.MessageData;
import reactor.core.publisher.Mono;

/**
 * DiscordChatSender
 * 
 * Class reponsible for sending chats to discord channel
 * 
 * @author zildjian
 * @since 2021.04.17.
 * 
 */
public class DiscordChatSender {
	private List<Snowflake> channels = null;
	private DiscordClient client = null;

	public DiscordChatSender(DiscordClient client) {
		this.client = client;
		channels = new ArrayList<Snowflake>();
		
		Configuration.getInstance().getDiscord().getChannels().forEach(channel -> {
			channels.add(Snowflake.of(channel));
		});
	}
	
	public void SendMessage(String message) {
		channels.forEach(channel -> {
			Mono<MessageData> res = client.getChannelById(channel).createMessage(message);
			res.subscribe(msg -> {
				if (Configuration.getInstance().getDebug()) {
					System.out.println(message+ " : "+msg);
				}
			});
		});
	}
}
