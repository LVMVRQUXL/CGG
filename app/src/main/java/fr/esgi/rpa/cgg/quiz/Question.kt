package fr.esgi.rpa.cgg.quiz

import fr.esgi.rpa.cgg.color.SingleColor
import fr.esgi.rpa.cgg.color.SuggestionColors

class Question(private val answer: SingleColor, private val suggestionColors: SuggestionColors) {
    override fun equals(other: Any?): Boolean = other is Question
            && this.answer.hashCode() == other.answer.hashCode()

    override fun hashCode(): Int = this.answer.hashCode() + this.suggestionColors.hashCode()

    override fun toString(): String =
        "Question(answer=${this.answer}, suggestionColors=${this.suggestionColors}"
}