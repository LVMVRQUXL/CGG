package fr.esgi.rpa.cgg.color

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.rpa.cgg.BaseActivity
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.utils.InternetCheck
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : BaseActivity() {
    private val colors: MutableList<Color> = mutableListOf()
    private val colorsAdapter: ColorsAdapter =
        ColorsAdapter(this.colors) { color: Int -> updateBackgroundWithTimer(color) }

    override fun continueOnCreate() {
        super.setContentView(R.layout.activity_colors)
        this.initColors()
        this.applyRecyclerView()
        this.setBackButtonClickListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (InternetCheck.internetWorking(this)) this.continueOnCreate()
        else InternetCheck.showAlert(this)
    }

    private fun applyRecyclerView(): RecyclerView? = colors_recycler_view?.apply {
        layoutManager = LinearLayoutManager(this@ColorsActivity)
        adapter = colorsAdapter
    }

    private fun getDefaultBackground(): Int = ContextCompat.getColor(this, R.color.primaryDark)

    private fun initColors() {
        val callback = GetColorsCallback(this.colors) { this.colorsAdapter.notifyDataSetChanged() }
        ColorsRepository.getColors(callback)
    }

    private fun setBackButtonClickListener() = back_button?.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        super.navigateUpTo(intent)
    }

    private fun updateBackground(backgroundColor: Int) {
        this.colorsAdapter.updateIsClickable()
        colors_activity?.setBackgroundColor(backgroundColor)
        window.statusBarColor = backgroundColor
    }

    private fun updateBackgroundWithTimer(backgroundColor: Int) {
        val timer = ColorTimer { this.updateBackground(this.getDefaultBackground()) }
        this.updateBackground(backgroundColor)
        timer.start()
    }
}