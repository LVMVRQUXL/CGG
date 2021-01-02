package fr.esgi.rpa.cgg.color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(
    private val colors: List<Color>,
    private val buttonClickListener: (color: Int) -> Unit
) : RecyclerView.Adapter<ColorViewHolder>() {
    private var isClickable: Boolean = true

    override fun getItemCount(): Int = this.colors.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color: Color = this.colors[position]
        holder.bind(color)
        this.setClickListeners(holder, color)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ColorViewHolder(inflater, parent)
    }

    fun updateIsClickable() {
        this.isClickable = !this.isClickable
    }

    private fun callButtonClickListener(colorValue: String) {
        if (this.isClickable) {
            val color = android.graphics.Color.parseColor(colorValue)
            this.buttonClickListener(color)
        }
    }

    private fun setClickListeners(holder: ColorViewHolder, color: Color) {
        holder.setDarkButtonClickListener { this.callButtonClickListener(color.darkValue) }
        holder.setLightButtonClickListener { this.callButtonClickListener(color.lightValue) }
    }
}