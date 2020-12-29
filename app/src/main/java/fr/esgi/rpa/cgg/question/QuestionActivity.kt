package fr.esgi.rpa.cgg.question

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.result.ResultActivity
import kotlinx.android.synthetic.main.activity_question.*


class QuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        button1?.setOnClickListener { buttonClicked() }
        button2?.setOnClickListener { buttonClicked() }
        button3?.setOnClickListener { buttonClicked() }
        button4?.setOnClickListener { buttonClicked() }
    }

    private fun buttonClicked() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }
}
