package fr.esgi.rpa.cgg.score

import fr.esgi.rpa.cgg.difficulty.DifficultyPreferences

class Score(private val id: String, private val value: Int, private val roundsNumber: Int) {

    private val difficulties: List<String> = listOf(
        DifficultyPreferences.EASY,
        DifficultyPreferences.MEDIUM,
        DifficultyPreferences.HARD
    )

    fun id() = this.id

    fun difficulty() = when(this.roundsNumber) {
            15 -> difficulties[2]
            10 -> difficulties[1]
            else -> difficulties[0]
    }

    fun value() = this.value.toString() + "/" + this.roundsNumber.toString()
}