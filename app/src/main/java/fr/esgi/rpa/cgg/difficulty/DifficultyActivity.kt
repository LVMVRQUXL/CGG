package fr.esgi.rpa.cgg.difficulty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import fr.esgi.rpa.cgg.BaseActivity
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.utils.ButtonUtils
import kotlinx.android.synthetic.main.activity_difficulty.*

class DifficultyActivity : BaseActivity() {
    private val buttons: MutableList<Button?> = mutableListOf()
    private var preferences: DifficultyPreferences? = null

    override fun continueOnCreate() {
        this.initButtons()
        this.initPreferences()
        this.focusButtonFromPreferences()
        this.setClickListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_difficulty)
        this.continueOnCreate()
    }

    private fun focusButtonFromPreferences() {
        val buttonId: Int? = this.getButtonIdFromPreferences()
        this.updateFocusedButton(buttonId)
    }

    private fun getButtonIdFromPreferences(): Int? = when (this.preferences?.value()) {
        DifficultyPreferences.EASY -> easy_button?.id
        DifficultyPreferences.MEDIUM -> medium_button?.id
        else -> hard_button?.id
    }

    private fun initButtons() {
        this.buttons.add(easy_button)
        this.buttons.add(medium_button)
        this.buttons.add(hard_button)
    }

    private fun initPreferences() {
        this.preferences = DifficultyPreferences(this)
    }

    private fun setBackButtonClickListener() = back_button?.setOnClickListener { button ->
        ButtonUtils.focus(button as Button)
        super.navigateUpTo(Intent(this, MainActivity::class.java))
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
        if (buttonId == button?.id) ButtonUtils.focus(button)
        else ButtonUtils.reset(button, this)
    }

    private fun updateFocusedButtonAndPreferences(buttonId: Int) {
        this.updateFocusedButton(buttonId)
        this.updatePreferences(buttonId)
    }
}