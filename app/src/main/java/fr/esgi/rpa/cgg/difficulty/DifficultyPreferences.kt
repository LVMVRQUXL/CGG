package fr.esgi.rpa.cgg.difficulty

import android.content.Context
import android.content.SharedPreferences

class DifficultyPreferences(private val context: Context) {
    companion object {
        const val EASY: String = "EASY"
        const val HARD: String = "HARD"
        const val MEDIUM: String = "MEDIUM"
        private const val DIFFICULTY_KEY: String = "difficultyKey"
        private const val MODE: Int = Context.MODE_PRIVATE
        private const val NAME: String = "Difficulty Preferences"
    }

    private val difficulties: List<String> = listOf(EASY, MEDIUM, HARD)
    private val preferences: SharedPreferences = this.context.getSharedPreferences(NAME, MODE)

    fun easy() = this.update(EASY)

    fun hard() = this.update(HARD)

    fun medium() = this.update(MEDIUM)

    fun roundsNumber(): Int = when (this.value()) {
        HARD -> 15
        MEDIUM -> 10
        else -> 5
    }

    fun value(): String? = this.preferences.getString(DIFFICULTY_KEY, this.difficulties.first())

    private fun update(difficulty: String) {
        with(this.preferences.edit()) {
            putString(DIFFICULTY_KEY, difficulty)
            apply()
        }
    }
}