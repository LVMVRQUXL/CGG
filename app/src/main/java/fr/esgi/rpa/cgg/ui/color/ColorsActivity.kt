package fr.esgi.rpa.cgg.ui.color

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import fr.esgi.rpa.cgg.core.ColorTimer
import fr.esgi.rpa.cgg.data.Color
import kotlinx.android.synthetic.main.activity_colors.*
import java.util.*

class ColorsActivity : AppCompatActivity() {
    private val colors: MutableList<Color> = mutableListOf()
    private val colorsAdapter: ColorsAdapter =
        ColorsAdapter(colors) { color: Int -> this.updateBackgroundWithTimer(color) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)
        this.initColors()
        this.applyRecyclerView()
        this.setClickListeners()
    }

    private fun applyRecyclerView(): RecyclerView? = colors_recycler_view?.apply {
        layoutManager = LinearLayoutManager(this@ColorsActivity)
        adapter = colorsAdapter
    }

    private fun getDefaultBackground(): Int = ContextCompat.getColor(this, R.color.colorPrimaryDark)

    // TODO: remove with API calls
    private fun initColors() {
        for (i in 1..10) {
            val red = Color("red", "#e74c3c", "#c0392b")
            val yellow = Color("yellow", "#F1C40F", "#f0ac00")
            this.colors.add(red)
            this.colors.add(yellow)
        }
    }

    private fun setClickListeners(): Unit? = home_button?.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun updateBackground(backgroundColor: Int) {
        this.colorsAdapter.updateIsClickable()
        colors_activity?.setBackgroundColor(backgroundColor)
    }

    private fun updateBackgroundWithTimer(backgroundColor: Int) {
        val timer = ColorTimer { this.updateBackground(this.getDefaultBackground()) }
        this.updateBackground(backgroundColor)
        timer.start()
    }
}