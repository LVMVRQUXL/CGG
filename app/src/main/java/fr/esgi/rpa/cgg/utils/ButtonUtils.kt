package fr.esgi.rpa.cgg.utils

import android.content.Context
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import fr.esgi.rpa.cgg.R

object ButtonUtils {
    private const val DARK_BACKGROUND: Int = R.drawable.rounded_corners_primary_dark
    private const val DEFAULT_BACKGROUND: Int = R.drawable.rounded_corners_primary
    private const val FOCUS_BACKGROUND: Int = R.drawable.rounded_corners_complementary
    private const val PRIMARY_COLOR: Int = R.color.primary
    private const val PRIMARY_DARK_COLOR: Int = R.color.primaryDark

    fun focus(button: Button?) = button?.setBackgroundResource(FOCUS_BACKGROUND)

    fun goodAnswer(button: Button?, context: Context) {
        button?.setBackgroundResource(DARK_BACKGROUND)
        this.textColor(button, PRIMARY_COLOR, context)
    }

    fun invisible(button: Button?) {
        button?.isInvisible = true
    }

    fun notClickable(button: Button?) {
        button?.isClickable = false
        button?.isEnabled = false
    }

    fun reset(button: Button?, context: Context) {
        this.clickable(button)
        button?.setBackgroundResource(DEFAULT_BACKGROUND)
        this.textColor(button, PRIMARY_DARK_COLOR, context)
    }

    fun text(button: Button?, text: String?) {
        button?.text = text
    }

    fun visible(button: Button?) {
        button?.isInvisible = false
    }

    private fun clickable(button: Button?) {
        button?.isClickable = true
        button?.isEnabled = true
    }

    private fun textColor(button: Button?, color: Int, context: Context) =
        button?.setTextColor(ContextCompat.getColor(context, color))
}