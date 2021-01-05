package fr.esgi.rpa.cgg.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.result.ResultActivity
import fr.esgi.rpa.cgg.utils.ButtonUtils
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private val suggestionButtons: MutableList<Button?> = mutableListOf()
    private var answerButton: Button? = null
    private var quizManager: QuizManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_quiz)
        this.initQuizManager()
        this.initSuggestionButtons()
        this.initCounters()

        answerButton = button1 // TODO: replace with real answer

        this.setOnClickListeners()
    }

    private fun goToResultActivity() =
        super.startActivity(Intent(this, ResultActivity::class.java))

    private fun initCounters() {
        this.updateCurrentRoundText()
        rounds_number?.text = this.quizManager?.getRoundsNumber().toString()
    }

    private fun initQuizManager() {
        this.quizManager = QuizManager(this)
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
        if (true == this.quizManager?.isLastRound())
            ButtonUtils.text(next_button, getString(R.string.result))
        ButtonUtils.invisible(next_button)
    }

    private fun resetSuggestionButtons() = this.suggestionButtons.forEach { button ->
        ButtonUtils.clickable(button)
        ButtonUtils.reset(button, this)
    }

    private fun revealAnswer() = ButtonUtils.goodAnswer(this.answerButton, this)

    private fun setNextButtonClickListener() = next_button?.setOnClickListener {
        this.quizManager?.nextRound { this.goToResultActivity() }
        this.nextRound()
    }

    private fun setOnClickListeners() {
        this.setNextButtonClickListener()
        this.setSuggestionButtonsClickListener()
    }

    private fun setSuggestionButtonsClickListener() = this.suggestionButtons.forEach { suggestion ->
        suggestion?.setOnClickListener { clickedButton ->
            val button = clickedButton as Button
            ButtonUtils.focus(button)
            this.revealAnswer()
            ButtonUtils.notClickable(button)
            ButtonUtils.visible(next_button)
        }
    }

    private fun updateCurrentRoundText() {
        current_round?.text = this.quizManager?.getCurrentRound().toString()
    }
}
