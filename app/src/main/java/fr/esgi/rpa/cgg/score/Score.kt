package fr.esgi.rpa.cgg.score

import fr.esgi.rpa.cgg.difficulty.DifficultyPreferences

class Score(val id: Int, private val value: Int, private val roundsNumber: Int) {
    private val difficulties: List<String> = listOf(
        DifficultyPreferences.EASY,
        DifficultyPreferences.MEDIUM,
        DifficultyPreferences.HARD
    )

    fun difficulty() = when (this.roundsNumber) {
        15 -> difficulties[2]
        10 -> difficulties[1]
        else -> difficulties[0]
    }

    fun value() = "${this.value}/${this.roundsNumber}"
}