package fr.esgi.rpa.cgg.color

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.rpa.cgg.R

class ColorViewHolder(inflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean = false) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.color_item, parent, attachToRoot)) {
    private var colorName: TextView? = null
    private var darkButton: Button? = null
    private var darkColor: String = ""
    private var lightColor: String = ""
    private var lightButton: Button? = null

    init {
        this.colorName = itemView.findViewById(R.id.color_name)
        this.darkButton = itemView.findViewById(R.id.color_dark_button)
        this.lightButton = itemView.findViewById(R.id.color_light_button)
    }

    fun bind(color: Color) {
        this.colorName?.text = color.name()
        this.darkColor = color.darkValue()
        this.lightColor = color.lightValue()
    }

    fun setDarkButtonClickListener(clickListener: () -> Unit): Unit? =
        this.darkButton?.setOnClickListener { clickListener() }

    fun setLightButtonClickListener(clickListener: () -> Unit): Unit? =
        this.lightButton?.setOnClickListener { clickListener() }
}