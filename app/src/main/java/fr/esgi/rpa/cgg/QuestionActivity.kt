package fr.esgi.rpa.cgg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.question_page.*


class QuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_page)

        button1?.setOnClickListener { buttonClicked() }
        button2?.setOnClickListener { buttonClicked() }
        button3?.setOnClickListener { buttonClicked() }
        button4?.setOnClickListener { buttonClicked() }
    }

    fun buttonClicked() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }
}
