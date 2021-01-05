package fr.esgi.rpa.cgg.quiz

import fr.esgi.rpa.cgg.color.SingleColor
import fr.esgi.rpa.cgg.color.SuggestionColors

class Question(private val answer: SingleColor, private val suggestionColors: SuggestionColors) {
    fun getAnswer(): SingleColor = this.answer

    fun getSuggestionColors(): SuggestionColors = this.suggestionColors

    override fun equals(other: Any?): Boolean = other is Question && this.answer == other.answer

    override fun hashCode(): Int = this.answer.hashCode() + this.suggestionColors.hashCode()

    override fun toString(): String =
        "Question(answer=${this.answer}, suggestionColors=${this.suggestionColors}"
}