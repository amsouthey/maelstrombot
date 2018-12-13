package com.alicesouthey.maelstrombot.random

import javax.inject.Inject
import kotlin.random.Random

class DiceRoller @Inject constructor(private val random: Random) {
	fun pbtaRoll(mod: Int, advantage: Advantage = Advantage.NONE): PbtaResult {
		when (advantage) {
			Advantage.NONE -> {
				val rolled = listOf<Int>(d6(), d6())
				return PbtaResult(rolled, mod, rolled.sum() + mod)
			}
			Advantage.ADVANTAGE -> {
				val rolled = listOf<Int>(d6(), d6(), d6())
				val kept = rolled.sorted().drop(1)
				return PbtaResult(rolled, mod, kept.sum() + mod, advantage, kept)
			}
			Advantage.DISADVANTAGE -> {
				val rolled = listOf<Int>(d6(), d6(), d6())
				val kept = rolled.sorted().dropLast(1)
				return PbtaResult(rolled, mod, kept.sum() + mod, advantage, kept)
			}
		}
	}

	private fun d6() = random.nextInt(1, 7)

	data class PbtaResult(
		val rolled: List<Int>,
		val mod: Int,
		val total: Int,
		val advantage: Advantage = Advantage.NONE,
		val kept: List<Int> = rolled
	)

	enum class Advantage {
		ADVANTAGE,
		DISADVANTAGE,
		NONE
	}
}
