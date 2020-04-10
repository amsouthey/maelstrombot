package com.alicesouthey.maelstrombot.listeners

import com.alicesouthey.maelstrombot.random.DiceRoller
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.util.regex.Pattern

class PbtaListener(private val diceRoller: DiceRoller) : ListenerAdapter() {
	private val pattern =
		Pattern.compile("^!roll(?:\\h+(?<mod>[+|-]?\\d+)?(?<adv>[a|d])?)?.*", Pattern.CASE_INSENSITIVE)

	override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
		if (event.author.isBot) {
			return
		}

		val matcher = pattern.matcher(event.message.contentStripped)
		if (!matcher.matches()) {
			return
		}
		val mod: Int = matcher.group("mod")?.toInt() ?: 0
		val advantage = when (matcher.group("adv")) {
			"a" -> DiceRoller.Advantage.ADVANTAGE
			"d" -> DiceRoller.Advantage.DISADVANTAGE
			else -> DiceRoller.Advantage.NONE
		}

		val roll = diceRoller.pbtaRoll(mod, advantage)
		val details = "${roll.kept}${if (roll.mod < 0) {
			" ${roll.mod}"
		} else if (roll.mod > 0) {
			" +${roll.mod}"
		} else {
			""
		}}"
		val hitMessage = when {
			roll.total < 7 -> "6- a miss!"
			roll.total < 10 -> "7-9 a weak hit."
			else -> "10+ a strong hit!"
		}


		event.channel.sendMessage(
			"${event.author.getAsMention()} rolled ${roll.total} ($details). $hitMessage"
		).queue()


	}
}