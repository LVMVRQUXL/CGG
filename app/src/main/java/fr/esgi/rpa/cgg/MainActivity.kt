package fr.esgi.rpa.cgg

import android.content.Intent
import android.os.Bundle
import fr.esgi.rpa.cgg.color.ColorsActivity
import fr.esgi.rpa.cgg.difficulty.DifficultyActivity
import fr.esgi.rpa.cgg.quiz.QuizActivity
import fr.esgi.rpa.cgg.score.ScoresActivity
import fr.esgi.rpa.cgg.score.ScoresCsvManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun continueOnCreate() {
        this.setClickListeners()
        this.setLastScore()
    }

    override fun onBackPressed() = finishAffinity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_main)
        this.continueOnCreate()
    }

    private fun setClickListeners() {
        this.setPlayButtonClickListener()
        this.setColorsButtonClickListener()
        this.setOptionButtonClickListener()
        this.setScoresButtonClickListener()
    }

    private fun setColorsButtonClickListener() = colors_button?.setOnClickListener {
        val intent = Intent(this, ColorsActivity::class.java)
        startActivity(intent)
    }

    private fun setOptionButtonClickListener() = options_button?.setOnClickListener {
        val intent = Intent(this, DifficultyActivity::class.java)
        startActivity(intent)
    }

    private fun setScoresButtonClickListener() {
        scores_button?.setOnClickListener {
            val intent = Intent(this, ScoresActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setPlayButtonClickListener() = play_button?.setOnClickListener {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }

    private fun setLastScore() {
        val scoresCsvManager = ScoresCsvManager(this)
        val lastScore = scoresCsvManager.lastScore()
        if (lastScore != null) {
            lastScoreText?.text = "Last score :"
            lastScoreValue?.text = lastScore.value()
        }

    }
}