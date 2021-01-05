package fr.esgi.rpa.cgg.quiz

import android.content.Context
import android.util.Log
import fr.esgi.rpa.cgg.color.*
import fr.esgi.rpa.cgg.difficulty.DifficultyPreferences

class QuizManager(private val context: Context) {
    companion object {
        private const val TAG: String = "QuizManager"
    }

    private val questions: MutableList<Question> = mutableListOf()
    private val randomColors: MutableList<SingleColor> = mutableListOf()
    private var currentQuestion: Question? = null
    private var currentRound: Int = 1
    private var roundsNumber: Int = -1

    init {
        this.initRoundsNumber()
        this.initRandomColors()
    }

    fun getCurrentRound(): Int = this.currentRound

    fun getRoundsNumber(): Int = this.roundsNumber

    fun isLastRound(): Boolean = this.roundsNumber == this.currentRound

    fun nextRound(nextActivity: () -> Unit) {
        this.currentRound++
        if (this.roundsNumber < this.currentRound) nextActivity()
        else this.nextQuestion()
    }

    private fun buildQuestion(): Question {
        val suggestionColors = SuggestionColors()
        while (!suggestionColors.isFull()) {
            val color = this.randomColors.random()
            if (!suggestionColors.contains(color)) suggestionColors.add(color)
        }
        val answer = suggestionColors.getRandomColor()
        return Question(answer, suggestionColors)
    }

    private fun continueInit() {
        this.initQuestions()
        this.nextQuestion()
    }

    private fun initQuestions() {
        while (this.roundsNumber > this.questions.size) {
            val question = this.buildQuestion()
            if (!this.questions.contains(question)) this.questions.add(question)
        }
    }

    private fun initRandomColors() {
        val colors: MutableList<Color> = mutableListOf()
        val callback = GetColorsCallback(colors) {
            pickRandomColors(colors)
            this.continueInit()
        }
        ColorsRepository.getColors(callback)
    }

    private fun initRoundsNumber() {
        this.roundsNumber = DifficultyPreferences(this.context).roundsNumber()
    }

    private fun nextQuestion() {
        this.currentQuestion = this.questions[this.currentRound - 1]
        Log.v(TAG, "${this.currentQuestion}")
    }

    private fun pickRandomColors(colors: List<Color>) {
        while (this.roundsNumber > this.randomColors.size) {
            val singleColor: SingleColor = colors.random().toSingleColor()
            if (!this.randomColors.contains(singleColor)) this.randomColors.add(singleColor)
        }
    }
}