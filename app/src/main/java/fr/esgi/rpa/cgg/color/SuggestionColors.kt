package fr.esgi.rpa.cgg.color

import android.util.Log

class SuggestionColors {
    companion object {
        private const val COLORS_LIMIT: Int = 4
        private const val TAG: String = "SuggestionColors"
    }

    private val colors: MutableList<SingleColor> = mutableListOf()

    fun add(color: SingleColor) = when (this.isFull()) {
        true -> Log.e(TAG, "Unable to add $color: limit is $COLORS_LIMIT")
        else -> this.colors.add(color)
    }

    fun contains(color: SingleColor): Boolean = this.colors.contains(color)

    override fun equals(other: Any?): Boolean =
        other is SuggestionColors && this.colors.hashCode() == other.colors.hashCode()

    fun getRandomColor(): SingleColor = this.colors.random()

    override fun hashCode(): Int = this.colors.hashCode()

    fun isFull(): Boolean = COLORS_LIMIT == this.colors.size

    override fun toString(): String = "SuggestionColors(colors=${this.colors})"
}