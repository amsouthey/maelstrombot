package com.alicesouthey.maelstrombot.app

import com.alicesouthey.maelstrombot.di.DaggerMaelstromAppComponent
import net.dv8tion.jda.core.JDABuilder
import org.slf4j.LoggerFactory
import javax.inject.Inject


fun main(args: Array<String>) {
	val logger = LoggerFactory.getLogger("com.alicesouthey.maelstrombot.app.main")
	val botToken: String? = when {
		args.size > 0 -> args[0]
		else -> System.getenv("MAELSTROM_BOT_TOKEN")
	}

	if (botToken == null || botToken == "bad") {
		logger.error(
			"MaelstromBot requires a discord bot token, either as a command line argument or in a MAELSTROM_BOT_TOKEN environment variable."
		)
		return
	}
	val builder = DaggerMaelstromAppComponent.builder()
		.botToken(botToken)
		.build().jdaBuilder()

	val discord = builder.build()
	discord.awaitReady()
}