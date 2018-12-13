package com.alicesouthey.maelstrombot.di

import dagger.BindsInstance
import dagger.Component
import net.dv8tion.jda.core.JDABuilder
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DiscordModule::class, RandomModule::class))
interface MaelstromAppComponent {
	fun jdaBuilder(): JDABuilder

	@Component.Builder
	interface Builder {
		@BindsInstance fun botToken(@DiscordModule.BotToken botToken: String): Builder

		fun build(): MaelstromAppComponent
	}
}