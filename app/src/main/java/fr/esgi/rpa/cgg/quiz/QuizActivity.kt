package fr.esgi.rpa.cgg.quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.color.SingleColor
import fr.esgi.rpa.cgg.result.ResultActivity
import fr.esgi.rpa.cgg.utils.ButtonUtils
import fr.esgi.rpa.cgg.utils.InternetCheck
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {
    companion object {
        private const val RESULT_STRING: Int = R.string.result
        private const val VIEW: Int = R.layout.activity_quiz
    }

    private val suggestionButtons: MutableList<Button?> = mutableListOf()
    private var answerButton: Button? = null
    private var quizManager: QuizManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val connectivity = InternetCheck(this)
        if (!connectivity.internetWorking()) {
            connectivity.showAlert(this)
        } else {
            this.initQuizManager()
        }
    }

    private fun continueOnCreate() {
        super.setContentView(VIEW)
        this.initButtons()
        this.initCounters()
        this.setOnClickListeners()
    }

    private fun disableSuggestionButtons() =
        this.suggestionButtons.forEach { button: Button? -> ButtonUtils.notClickable(button) }

    private fun goToResultActivity(score: Int, roundsNumber: Int) = super.startActivity(
        Intent(this, ResultActivity::class.java)
            .putExtra(ResultActivity.SCORE_KEY, score.toString())
            .putExtra(ResultActivity.ROUNDS_NUMBER_KEY, roundsNumber.toString())
    )

    private fun initAnswerButton() {
        val answer: SingleColor? = this.quizManager?.getCurrentQuestion()?.getAnswer()
        this.answerButton =
            this.suggestionButtons.first { button: Button? -> answer?.getName() == button?.text }
    }

    private fun initButtons() {
        this.initSuggestionButtons()
        this.initAnswerButton()
        this.updateBackgroundColor()
    }

    private fun initCounters() {
        this.updateCurrentRoundText()
        rounds_number?.text = this.quizManager?.getRoundsNumber().toString()
    }

    private fun initQuizManager() {
        this.quizManager = QuizManager(this) { this.continueOnCreate() }
    }

    private fun initSuggestionButtons() {
        val colors: List<SingleColor>? =
            this.quizManager?.getCurrentQuestion()?.getSuggestionColors()?.getColors()
        val buttons = listOf<Button?>(button1, button2, button3, button4)
        buttons.forEachIndexed { index: Int, button: Button? ->
            val colorName = colors?.get(index)?.getName()
            ButtonUtils.text(button, colorName)
            this.suggestionButtons.add(button)
        }
    }

    private fun nextRound() {
        this.updateCurrentRoundText()
        this.resetSuggestionButtons()
        if (true == this.quizManager?.isLastRound())
            ButtonUtils.text(next_button, super.getString(RESULT_STRING))
        ButtonUtils.invisible(next_button)
        this.initButtons()
    }

    private fun resetSuggestionButtons() = this.suggestionButtons.forEach { button: Button? ->
        ButtonUtils.reset(button, this)
    }

    private fun revealAnswer() = ButtonUtils.goodAnswer(this.answerButton, this)

    private fun setNextButtonClickListener() = next_button?.setOnClickListener {
        this.quizManager?.nextRound { score: Int, roundsNumber: Int ->
            this.goToResultActivity(score, roundsNumber)
        }
        this.nextRound()
    }

    private fun setOnClickListeners() {
        this.setNextButtonClickListener()
        this.setSuggestionButtonsClickListener()
    }

    private fun setSuggestionButtonsClickListener() = this.suggestionButtons.forEach { b: Button? ->
        b?.setOnClickListener { clickedButton ->
            val button = clickedButton as Button
            this.quizManager?.checkAnswer(button.text as String)
            ButtonUtils.focus(button)
            this.disableSuggestionButtons()
            this.revealAnswer()
            ButtonUtils.visible(next_button)
        }
    }

    private fun updateBackgroundColor() {
        val colorValue: String? = this.quizManager?.getCurrentQuestion()?.getAnswer()?.getValue()
        val color = Color.parseColor(colorValue)
        background?.setBackgroundColor(color)
        window.statusBarColor = color
    }

    private fun updateCurrentRoundText() {
        current_round?.text = this.quizManager?.getCurrentRound().toString()
    }
}
