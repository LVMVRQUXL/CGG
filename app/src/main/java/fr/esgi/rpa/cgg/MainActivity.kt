package fr.esgi.rpa.cgg

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.rpa.cgg.color.ColorsActivity
import fr.esgi.rpa.cgg.option.OptionActivity
import fr.esgi.rpa.cgg.question.QuestionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setClickListeners()

        val preferences = initOptionSharedPreferences()
        val difficultyOption = preferences.getString(OptionActivity.DIFFICULTY_KEY, null)
        Log.v("MainActivity", "DIFFICULTY_KEY = $difficultyOption")
    }

    private fun initOptionSharedPreferences(): SharedPreferences =
        getSharedPreferences(getString(R.string.optionPreference), Context.MODE_PRIVATE)

    private fun setClickListeners() {
        this.setPlayButtonClickListener()
        this.setColorsButtonClickListener()
        this.setOptionButtonClickListener()
    }

    private fun setColorsButtonClickListener() {
        colors_button?.setOnClickListener {
            val intent = Intent(this, ColorsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setOptionButtonClickListener() {
        options_button?.setOnClickListener {
            val intent = Intent(this, OptionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setPlayButtonClickListener() {
        play_button?.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}