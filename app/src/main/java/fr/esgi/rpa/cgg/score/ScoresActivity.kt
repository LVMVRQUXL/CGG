package fr.esgi.rpa.cgg.score

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import kotlinx.android.synthetic.main.activity_scores.*

class ScoresActivity : AppCompatActivity()  {

    private var scores : MutableList<Score> = mutableListOf()
    private val scoresAdapter: ScoresAdapter = ScoresAdapter(this.scores)
    private var scoresCsvManager: ScoresCsvManager? = null
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        this.initScoresCsvManager()
        this.initScores()
        this.updateCountScores()
        this.applyRecyclerView()
        this.setBackButtonClickListener()
    }

    private fun applyRecyclerView() : RecyclerView? = scores_recycler_view?.apply {
        layoutManager = LinearLayoutManager(this@ScoresActivity)
        adapter = scoresAdapter
    }

    private fun initScoresCsvManager() {
        this.scoresCsvManager = ScoresCsvManager(this)
    }

    private fun initScores() {
        scoresCsvManager?.read()?.forEach { score ->
            this.scores.add(score)
            this.count++
        }
    }

    private fun updateCountScores() {
        countScores?.text = this.count.toString()
        Log.v("Score", "Count : ${this.count}")
    }

    private fun setBackButtonClickListener() {
        back_button?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            navigateUpTo(intent)
        }
    }


}