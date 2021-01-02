package fr.esgi.rpa.cgg.question

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.result.ResultActivity
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    private val suggestionButtons: MutableList<Button?> = mutableListOf()
    private val roundsNumber: Int = 5
    private var currentRound: Int = 1
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        this.suggestionButtons.add(button1)
        this.suggestionButtons.add(button2)
        this.suggestionButtons.add(button3)
        this.suggestionButtons.add(button4)

        rounds_number?.text = this.roundsNumber.toString()
        current_round?.text = this.currentRound.toString()

        next?.setOnClickListener { this.nextClicked() }
        for (suggestion in this.suggestionButtons)
            suggestion?.setOnClickListener { buttonClicked ->
                this.buttonClicked(buttonClicked as Button)
            }
    }

    private fun buttonClicked(button: Button) {
        if (button1?.id == button.id) {
            button.setTextColor(Color.GREEN)
            this.score++
        } else {
            button.setTextColor(Color.RED)
            for (suggestion in this.suggestionButtons)
                if (button1?.id == suggestion?.id) suggestion?.setTextColor(Color.GREEN)
        }
        for (suggestion in this.suggestionButtons) {
            suggestion?.isEnabled = false
            suggestion?.isClickable = false
        }
    }

    private fun nextClicked() {
        this.currentRound++
        if (this.roundsNumber < this.currentRound) {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        } else {
            current_round?.text = this.currentRound.toString()
            for (suggestion in this.suggestionButtons) {
                suggestion?.isEnabled = true
                suggestion?.isClickable = true
                val color = ContextCompat.getColor(this, R.color.colorPrimaryDark)
                suggestion?.setTextColor(color)
            }
            if (this.roundsNumber == this.currentRound) next?.text = getString(R.string.result)
        }
    }
}
