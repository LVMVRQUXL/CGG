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
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ColorsActivity : BaseActivity() {
    companion object {
        private const val SCOPE_NAME: String = "ColorsActivity"
    }

    private val colors: MutableList<Color> = mutableListOf()
    private val colorsAdapter: ColorsAdapter =
        ColorsAdapter(this.colors) { color: Int -> updateBackgroundWithTimer(color) }
    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))

    override fun continueOnCreate() {
        super.setContentView(R.layout.activity_colors)
        this.getColorsFromApi()
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

    private fun getColorsFromApi() = this.coroutineScope.launch(Dispatchers.IO) {
        val callback = GetColorsCallback(this@ColorsActivity.colors) {
            this@ColorsActivity.colorsAdapter.notifyDataSetChanged()
        }
        ColorsRepository.getColors(callback)
    }

    private fun getDefaultBackground(): Int = ContextCompat.getColor(this, R.color.primaryDark)

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