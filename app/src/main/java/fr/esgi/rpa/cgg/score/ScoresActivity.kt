package fr.esgi.rpa.cgg.score

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.rpa.cgg.BaseActivity
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import kotlinx.android.synthetic.main.activity_scores.*

class ScoresActivity : BaseActivity()  {

    private var scores : MutableList<Score> = mutableListOf()
    private val scoresAdapter: ScoresAdapter = ScoresAdapter(this.scores)
    private var scoresCsvManager: ScoresCsvManager? = null
    private var count = 0

    override fun continueOnCreate() {
        this.initScoresCsvManager()
        this.initScores()
        this.updateCountScores()
        this.applyRecyclerView()
        this.setBackButtonClickListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        this.continueOnCreate()
    }

    private fun applyRecyclerView() : RecyclerView? = scores_recycler_view?.apply {
        layoutManager = LinearLayoutManager(this@ScoresActivity)
        adapter = scoresAdapter
    }

    private fun initScoresCsvManager() {
        this.scoresCsvManager = ScoresCsvManager(this)
    }

    private fun initScores() {
        scoresCsvManager?.read()?.reversed()?.forEach { score ->
            this.scores.add(score)
            this.count++
        }
    }

    private fun updateCountScores() {
        countScores?.text = this.count.toString()
    }

    private fun setBackButtonClickListener() {
        back_button?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            navigateUpTo(intent)
        }
    }


}