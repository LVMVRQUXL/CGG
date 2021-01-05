package fr.esgi.rpa.cgg.color

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.rpa.cgg.MainActivity
import fr.esgi.rpa.cgg.R
import kotlinx.android.synthetic.main.activity_colors.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ColorsActivity : AppCompatActivity() {
    private var colors: MutableList<Color> = mutableListOf()
    private var colorsAdapter: ColorsAdapter =
        ColorsAdapter(colors) { color: Int -> updateBackgroundWithTimer(color) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)
        this.initColors()
        this.applyRecyclerView()
        this.setBackButtonClickListener()
    }

    private fun applyRecyclerView(): RecyclerView? = colors_recycler_view?.apply {
        layoutManager = LinearLayoutManager(this@ColorsActivity)
        adapter = colorsAdapter
    }

    private fun getDefaultBackground(): Int = ContextCompat.getColor(this, R.color.colorPrimaryDark)

    private fun initColors() = ColorsRepository.getAllColors(object : Callback<List<Color>> {
        override fun onResponse(call: Call<List<Color>>, response: Response<List<Color>>) {
            response.body()?.forEach { color: Color -> colors.add(color) }
            colorsAdapter.notifyDataSetChanged()
        }

        override fun onFailure(call: Call<List<Color>>, t: Throwable) {
            Log.e("ColorsActivity", "An error has occurred", t)
        }
    })

    private fun setBackButtonClickListener() = back_button?.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        navigateUpTo(intent)
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