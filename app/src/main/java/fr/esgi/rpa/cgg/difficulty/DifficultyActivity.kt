package fr.esgi.rpa.cgg.difficulty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import kotlinx.android.synthetic.main.activity_difficulty.*

class DifficultyActivity : AppCompatActivity() {
    private val buttons: MutableList<Button?> = mutableListOf()
    private var preferences: DifficultyPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_difficulty)
        this.initButtons()
        this.initPreferences()
        this.focusButtonFromPreferences()
        this.setClickListeners()
    }

    private fun focusButton(button: Button?) =
        button?.setBackgroundResource(R.drawable.rounded_corners_complementary)

    private fun focusButtonFromPreferences() {
        val buttonId: Int? = this.getButtonIdFromPreferences()
        this.updateFocusedButton(buttonId)
    }

    private fun getButtonIdFromPreferences(): Int? = when (this.preferences?.value()) {
        DifficultyPreferences.EASY -> easy_button?.id
        DifficultyPreferences.MEDIUM -> medium_button?.id
        else -> easy_button?.id
    }

    private fun initButtons() {
        this.buttons.add(easy_button)
        this.buttons.add(medium_button)
        this.buttons.add(hard_button)
    }

    private fun initPreferences() {
        this.preferences = DifficultyPreferences(this)
    }

    private fun resetButtonState(button: Button?) =
        button?.setBackgroundResource(R.drawable.rounded_corners_primary)

    private fun setBackButtonClickListener() = back_button?.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        super.navigateUpTo(intent)
    }

    private fun setClickListeners() {
        this.setBackButtonClickListener()
        this.setDifficultyButtonsClickListener()
    }

    private fun setDifficultyButtonsClickListener() = this.buttons.forEach { button ->
        button?.setOnClickListener { it -> this.updateFocusedButtonAndPreferences(it.id) }
    }

    private fun updatePreferences(buttonId: Int) = when (buttonId) {
        easy_button?.id -> this.preferences?.easy()
        medium_button?.id -> this.preferences?.medium()
        else -> this.preferences?.hard()
    }

    private fun updateFocusedButton(buttonId: Int?) = this.buttons.forEach { button ->
        if (buttonId == button?.id) this.focusButton(button)
        else this.resetButtonState(button)
    }

    private fun updateFocusedButtonAndPreferences(buttonId: Int) {
        this.updateFocusedButton(buttonId)
        this.updatePreferences(buttonId)
    }
}