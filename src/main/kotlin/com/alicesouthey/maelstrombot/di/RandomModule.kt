package com.alicesouthey.maelstrombot.di

import com.alicesouthey.maelstrombot.random.DiceRoller
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.random.Random

@Module
class RandomModule {
	@Singleton
	@Provides
	fun provideRandom(): Random = Random

	@Singleton
	@Provides
	fun provideDiceRoller(random: Random): DiceRoller = DiceRoller(random)
}