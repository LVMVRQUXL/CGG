package fr.esgi.rpa.cgg.result

import android.content.Intent
import android.os.Bundle
import fr.esgi.rpa.cgg.BaseActivity
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.quiz.QuizActivity
import fr.esgi.rpa.cgg.score.ScoresCsvManager
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : BaseActivity() {
    companion object {
        const val ROUNDS_NUMBER_KEY: String = "roundsNumberKey"
        const val SCORE_KEY: String = "scoreKey"
        private const val VIEW: Int = R.layout.activity_result
    }

    private var scoreValue: String? = null
    private var roundsNumber: String? = null
    private var scoresCsvManager: ScoresCsvManager? = null

    override fun continueOnCreate() {
        this.initValues()
        this.initTextViews()
        this.saveScore()
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

    private fun initValues() {
        this.scoreValue = super.getIntent()?.getStringExtra(SCORE_KEY)
        this.roundsNumber = super.getIntent()?.getStringExtra(ROUNDS_NUMBER_KEY)
    }

    private fun initTextViews() {
        score?.text = this.scoreValue
        rounds_number?.text = this.roundsNumber
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

    private fun saveScore() {
        val value = this.scoreValue?.toInt()
        val rounds = this.roundsNumber?.toInt()
        this.scoresCsvManager = ScoresCsvManager(this)
        if (value != null && rounds != null) {
            this.scoresCsvManager?.write(value, rounds)
        }
    }
}