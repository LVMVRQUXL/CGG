package fr.esgi.rpa.cgg.question

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.result.ResultActivity
import kotlinx.android.synthetic.main.activity_question.*
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.question_page.*


class QuestionActivity : AppCompatActivity() {
    private var round_number = 1
    private var difficulty = 5
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        round?.text = difficulty.toString()
        currentRound?.text = " $round_number"

        val buttons = arrayOf(button1, button2, button3, button4)
        next_result?.setOnClickListener { nextClicked(next_result, currentRound, buttons) }
        button1?.setOnClickListener { buttonClicked(button1, buttons) }
        button2?.setOnClickListener { buttonClicked(button2, buttons) }
        button3?.setOnClickListener { buttonClicked(button3, buttons) }
        button4?.setOnClickListener { buttonClicked(button4, buttons) }
    }

    private fun buttonClicked() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
        fun buttonClicked(v: Button, buttons: Array<Button>) {
            if (R.id.button1 == v.id) { // Right Answer
                v.setTextColor(Color.GREEN)
                score += 1
                Log.d(QuestionActivity::class.simpleName, "RIGHT  $round_number")
            } else { // Wrong Answer
                Log.d(QuestionActivity::class.simpleName, "WRONG  $round_number")
                v.setTextColor(Color.RED)
                for (item in buttons) {
                    if (item.id == R.id.button1) {
                        item.setTextColor(Color.GREEN)
                    }
                }
            }
            for (item in buttons) {
                item.isEnabled = false
                item.isClickable = false
            }
        }

        fun nextClicked(v: Button, round: TextView, buttons: Array<Button>) {
            round_number += 1

            if (round_number > difficulty) {
                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
            } else {
                round.text = " " + round_number.toString()
                for (item in buttons) {
                    item.isEnabled = true
                    item.isClickable = true
                    item.setTextColor(R.color.colorPrimaryDark)
                }
            }
            if (round_number == difficulty) v.text = "Result"
        }
    }
