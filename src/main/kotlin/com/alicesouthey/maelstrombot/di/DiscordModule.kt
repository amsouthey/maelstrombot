package com.alicesouthey.maelstrombot.di

import com.alicesouthey.maelstrombot.listeners.CallResponseListener
import com.alicesouthey.maelstrombot.listeners.PbtaListener
import com.alicesouthey.maelstrombot.random.DiceRoller
import dagger.Module
import dagger.Provides
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.hooks.EventListener
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.random.Random

@Module
class DiscordModule(){
	@Singleton @Provides fun provideJDA(@BotToken botToken: String, listeners: Array<EventListener>): JDA {
		val builder = JDABuilder(botToken)
			.addEventListener(*listeners)
			.build()
		builder.awaitReady()
		return builder
	}

	@Singleton @Provides fun provideListeners(diceRoller: DiceRoller): Array<EventListener> {
		return arrayOf(
			CallResponseListener.help(),
			CallResponseListener.info(),
			PbtaListener(diceRoller)
		)
	}

	@Qualifier
	@Retention(AnnotationRetention.RUNTIME)
	annotation class BotToken

}