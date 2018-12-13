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
class CallResponseListener(val trigger: String, val response: String) : ListenerAdapter() {
	private val pattern = Regex(trigger, RegexOption.IGNORE_CASE)
	override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
		if (!event.author.isBot && pattern.matches(event.message.contentDisplay)) {
			event.channel.sendMessage(response).queue()
		}
	}

	companion object {
		fun help() = CallResponseListener(
			"!help",
			"!pbta MOD [a|d]\tMake a PBTA roll with optional **MOD**ifier and **a**dvantage/**d**isadvantage."
		)

		fun info() = CallResponseListener(
			"!info",
			"MaelstromBot Copyright 2018 Alice Southey. This bot is built on the JDA library. \n" +
					"You can display a command list with !help."
		)
	}
}

