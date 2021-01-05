package fr.esgi.rpa.cgg

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.rpa.cgg.color.ColorsActivity
import fr.esgi.rpa.cgg.difficulty.DifficultyActivity
import fr.esgi.rpa.cgg.question.QuestionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setClickListeners()
    }

    private fun setClickListeners() {
        this.setPlayButtonClickListener()
        this.setColorsButtonClickListener()
        this.setOptionButtonClickListener()
    }

    private fun setColorsButtonClickListener() = colors_button?.setOnClickListener {
        val intent = Intent(this, ColorsActivity::class.java)
        startActivity(intent)
    }

    private fun setOptionButtonClickListener() = options_button?.setOnClickListener {
        val intent = Intent(this, DifficultyActivity::class.java)
        startActivity(intent)
    }

    private fun setPlayButtonClickListener() = play_button?.setOnClickListener {
        val intent = Intent(this, QuestionActivity::class.java)
        startActivity(intent)
    }
}