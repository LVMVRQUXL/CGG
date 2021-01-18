package fr.esgi.rpa.cgg.result

import android.content.Intent
import android.os.Bundle
import fr.esgi.rpa.cgg.BaseActivity
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.quiz.QuizActivity
import fr.esgi.rpa.cgg.score.Score
import fr.esgi.rpa.cgg.score.ScoresCsvManager
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : BaseActivity() {
    companion object {
        const val ROUNDS_NUMBER_KEY: String = "roundsNumberKey"
        const val SCORE_KEY: String = "scoreKey"
        private const val VIEW: Int = R.layout.activity_result
    }

    private var scoresCsvManager: ScoresCsvManager? = null

    override fun continueOnCreate() {
        this.initTextViews()
        this.saveScore("EASY", 5)
        this.setClickListeners()
    }

    override fun onBackPressed() {
        super.navigateUpTo(Intent(this, MainActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(VIEW)
        this.continueOnCreate()
    }

    private fun initTextViews() {
        score?.text = super.getIntent()?.getStringExtra(SCORE_KEY)
        rounds_number?.text = super.getIntent()?.getStringExtra(ROUNDS_NUMBER_KEY)
    }

    private fun setBackButtonClickListener() =
        back_button?.setOnClickListener { this.onBackPressed() }

    private fun setClickListeners() {
        this.setBackButtonClickListener()
        this.setPlayButtonClickListener()
    }

    private fun setPlayButtonClickListener() = play_button?.setOnClickListener {
        val intent = Intent(this, QuizActivity::class.java)
        super.startActivity(intent)
    }

    private fun saveScore(difficulty: String, score: Int) {
        this.scoresCsvManager = ScoresCsvManager(this)
        this.scoresCsvManager?.write(difficulty, score)
    }
}