package fr.esgi.rpa.cgg

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_option.*


class OptionActivity : AppCompatActivity() {

    private var difficulty: String? = ""

    companion object {
        const val DIFFICULTY_KEY = "difficultyKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)
        this.setActualDifficulty()
        this.setClickListeners()

    }

    private fun setActualDifficulty() {
        val optionSharedPreferences = this?.getSharedPreferences(this.getString(R.string.optionPreference), Context.MODE_PRIVATE)
        difficulty = optionSharedPreferences.getString(DIFFICULTY_KEY, "EASY")
        when(difficulty) {
            "EASY" -> this.setButtonToFocusedState(easy_button, R.drawable.rounded_corners_green)
            "MEDIUM" -> this.setButtonToFocusedState(medium_button, R.drawable.rounded_corners_yellow)
            "HARD" -> this.setButtonToFocusedState(hard_button, R.drawable.rounded_corners_red)
        }
    }

    private fun setClickListeners() {
        this.setConfirmButtonClickListener()
        this.setEasyButtonClickListener()
        this.setMediumButtonClickListener()
        this.setHardButtonClickListener()
    }

    private fun setEasyButtonClickListener(): Unit? = easy_button?.setOnClickListener {
        this.setButtonToFocusedState(easy_button, R.drawable.rounded_corners_green)
        this.setButtonToDefaultState(medium_button)
        this.setButtonToDefaultState(hard_button)
        difficulty = "EASY"
    }

    private fun setMediumButtonClickListener(): Unit? = medium_button?.setOnClickListener {
        this.setButtonToFocusedState(medium_button, R.drawable.rounded_corners_yellow)
        this.setButtonToDefaultState(easy_button)
        this.setButtonToDefaultState(hard_button)
        difficulty = "MEDIUM"
    }

    private fun setHardButtonClickListener(): Unit? = hard_button?.setOnClickListener {
        this.setButtonToFocusedState(hard_button, R.drawable.rounded_corners_red)
        this.setButtonToDefaultState(easy_button)
        this.setButtonToDefaultState(medium_button)
        difficulty = "HARD"
    }

    private fun setButtonToFocusedState(button: Button, imageId: Int) {
        button?.isFocusable = true
        button?.setBackgroundResource(imageId)
    }

    private fun setButtonToDefaultState(button: Button) {
        button?.isFocusable = false
        button?.setBackgroundResource(R.drawable.rounded_corners_primary)
    }

    private fun setConfirmButtonClickListener(): Unit? = confirm_button?.setOnClickListener {
        val optionSharedPreferences = this?.getSharedPreferences(this.getString(R.string.optionPreference), Context.MODE_PRIVATE)
        with(optionSharedPreferences.edit()) {
            putString(DIFFICULTY_KEY, difficulty)
            apply()
        }
        val intent = Intent(this, MainActivity::class.java)
        this.navigateUpTo(intent)
    }

}