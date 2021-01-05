package fr.esgi.rpa.cgg.question

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.difficulty.DifficultyPreferences
import fr.esgi.rpa.cgg.result.ResultActivity
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    private val suggestionButtons: MutableList<Button?> = mutableListOf()
    private var answerButton: Button? = null
    private var currentRound: Int = 1
    private var roundsNumber: Int = -1
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_question)
        this.initRoundsNumber()
        this.initSuggestionButtons()
        this.initCounters()

        // TODO: get colors from API
        // TODO: get number of random colors equals to this.roundsNumber
        answerButton = button1 // TODO: replace with real answer

        this.setOnClickListeners()
    }

    private fun checkUserAnswer(userAnswer: Button) {
        if (this.answerButton?.id != userAnswer.id) userAnswer.setTextColor(Color.RED)
        else this.score++
        this.revealAnswer()
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
        this.updateNextButton(true)
    }

    private fun resetSuggestionButtons() = this.suggestionButtons.forEach { button ->
        this.updateSuggestionButtonsClickable(button, true)
        val color = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        button?.setTextColor(color)
    }

    private fun revealAnswer() =
        this.suggestionButtons.first { button -> this.answerButton?.id == button?.id }
            ?.setTextColor(Color.GREEN)

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
            this.updateSuggestionButtonsClickable(button, false)
            this.updateNextButton(false)
        }
    }

    private fun updateCurrentRoundText() {
        current_round?.text = this.currentRound.toString()
    }

    private fun updateNextButton(isInvisible: Boolean) {
        if (this.roundsNumber == this.currentRound) next_button?.text = getString(R.string.result)
        next_button?.isInvisible = isInvisible
    }

    private fun updateSuggestionButtonsClickable(button: Button?, isClickable: Boolean) {
        button?.isClickable = isClickable
        button?.isEnabled = isClickable
    }
}
