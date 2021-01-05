package fr.esgi.rpa.cgg.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.difficulty.DifficultyPreferences
import fr.esgi.rpa.cgg.result.ResultActivity
import fr.esgi.rpa.cgg.utils.ButtonUtils
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private val suggestionButtons: MutableList<Button?> = mutableListOf()
    private var answerButton: Button? = null
    private var currentRound: Int = 1
    private var roundsNumber: Int = -1
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_quiz)
        this.initRoundsNumber()
        this.initSuggestionButtons()
        this.initCounters()

        // TODO: get colors from API
        // TODO: get number of random colors equals to this.roundsNumber
        answerButton = button1 // TODO: replace with real answer

        this.setOnClickListeners()
    }

    private fun checkUserAnswer(userAnswer: Button) {
        if (this.answerButton?.id != userAnswer.id) ButtonUtils.focus(userAnswer)
        else this.score++
    }

    private fun goToResultActivity() =
        super.startActivity(Intent(this, ResultActivity::class.java))

    private fun initCounters() {
        this.updateCurrentRoundText()
        rounds_number?.text = this.roundsNumber.toString()
    }

    private fun initRoundsNumber() {
        this.roundsNumber = DifficultyPreferences(this).roundsNumber()
    }

    private fun initSuggestionButtons() {
        this.suggestionButtons.add(button1)
        this.suggestionButtons.add(button2)
        this.suggestionButtons.add(button3)
        this.suggestionButtons.add(button4)
    }

    private fun nextRound() {
        this.updateCurrentRoundText()
        this.resetSuggestionButtons()
        if (this.roundsNumber == this.currentRound)
            ButtonUtils.text(next_button, getString(R.string.result))
        ButtonUtils.invisible(next_button)
    }

    private fun resetSuggestionButtons() = this.suggestionButtons.forEach { button ->
        ButtonUtils.clickable(button)
        ButtonUtils.reset(button, this)
    }

    private fun revealAnswer() = ButtonUtils.goodAnswer(this.answerButton, this)

    private fun setNextButtonClickListener() = next_button?.setOnClickListener {
        this.currentRound++
        if (this.roundsNumber < this.currentRound) this.goToResultActivity()
        else this.nextRound()
    }

    private fun setOnClickListeners() {
        this.setNextButtonClickListener()
        this.setSuggestionButtonsClickListener()
    }

    private fun setSuggestionButtonsClickListener() = this.suggestionButtons.forEach { suggestion ->
        suggestion?.setOnClickListener { clickedButton ->
            val button = clickedButton as Button
            this.checkUserAnswer(button)
            this.revealAnswer()
            ButtonUtils.notClickable(button)
            ButtonUtils.visible(next_button)
        }
    }

    private fun updateCurrentRoundText() {
        current_round?.text = this.currentRound.toString()
    }
}
