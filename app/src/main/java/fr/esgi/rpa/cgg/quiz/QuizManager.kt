package fr.esgi.rpa.cgg.quiz

import android.content.Context
import fr.esgi.rpa.cgg.color.*
import fr.esgi.rpa.cgg.difficulty.DifficultyPreferences

class QuizManager(private val context: Context, private val callbackUI: () -> Unit) {
    private val questions: MutableList<Question> = mutableListOf()
    private val randomColors: MutableList<SingleColor> = mutableListOf()
    private val roundsNumber: Int = DifficultyPreferences(this.context).roundsNumber()
    private var currentQuestion: Question? = null
    private var currentRound: Int = 0

    init {
        this.initRandomColors()
    }

    fun getCurrentQuestion(): Question? = this.currentQuestion

    fun getCurrentRound(): Int = this.currentRound

    fun getRoundsNumber(): Int = this.roundsNumber

    fun isLastRound(): Boolean = this.roundsNumber == this.currentRound

    fun nextRound(nextActivity: () -> Unit) = when (this.roundsNumber) {
        this.currentRound -> nextActivity()
        else -> this.nextQuestion()
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

    private fun continueInit(colors: List<Color>) {
        this.pickRandomColors(colors)
        this.initQuestions()
        this.nextQuestion()
        this.callbackUI()
    }

    private fun initQuestions() {
        while (this.roundsNumber > this.questions.size) {
            val question = this.buildQuestion()
            if (!this.questions.contains(question)) this.questions.add(question)
        }
    }

    private fun initRandomColors() {
        val colors: MutableList<Color> = mutableListOf()
        val callback = GetColorsCallback(colors) { this.continueInit(colors) }
        ColorsRepository.getColors(callback)
    }

    private fun nextQuestion() {
        this.currentQuestion = this.questions[this.currentRound++]
    }

    private fun pickRandomColors(colors: List<Color>) {
        while (this.roundsNumber > this.randomColors.size) {
            val singleColor: SingleColor = colors.random().toSingleColor()
            if (!this.randomColors.contains(singleColor)) this.randomColors.add(singleColor)
        }
    }
}