package com.alicesouthey.maelstrombot.listeners

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

/**
 * Simple ListenerAdaptor that watches for an exact match of its trigger and sends its response to the triggering
 * channel.
 *
 * @param trigger Trigger phrase for this listener.
 * @param response Response to be sent back to the channel in which the trigger phrase appears.
 */
class CallResponseListener(private val trigger: String, private val response: String) : ListenerAdapter() {
	private val pattern = Regex(trigger, RegexOption.IGNORE_CASE)
	override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
		if (!event.author.isBot && pattern.matches(event.message.contentDisplay)) {
			event.channel.sendMessage(response).queue()
		}
	}

	companion object {
		fun help() = CallResponseListener(
			"!help",
			"!roll MOD [a|d]\tMake a PBTA roll with optional **MOD**ifier and **a**dvantage/**d**isadvantage."
		)

		fun info() = CallResponseListener(
			"!info",
			"MaelstromBot Copyright 2018 Alice Southey. \n" +
            "Report issues at https://github.com/amsouthey/maelstrombot\n" +
					"You can display a command list with !help."
		)
	}
}
