package fr.esgi.rpa.cgg.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.question.QuestionActivity
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    companion object {
        const val SCORE_KEY = "scoreKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        intent?.getStringExtra(SCORE_KEY)?.let {
            scoreResult?.text = it + "/10"
        }

        home_button?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        play_button?.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}