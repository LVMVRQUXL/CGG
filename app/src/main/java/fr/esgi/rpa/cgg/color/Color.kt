package fr.esgi.rpa.cgg.color

class Color(
    private val darkValue: String,
    private val lightValue: String,
    private val name: String
) {
    fun darkValue(): String = this.darkValue

    override fun equals(other: Any?): Boolean = other is Color
            && this.name == other.name
            && this.lightValue == other.lightValue
            && this.darkValue == other.darkValue

    fun lightValue(): String = this.lightValue

    fun name(): String = this.name

    override fun hashCode(): Int =
        this.name.hashCode() + this.lightValue.hashCode() + this.darkValue.hashCode()

    fun toSingleColor(): SingleColor = when (listOf(true, false).random()) {
        true -> SingleColor(this.name, this.lightValue)
        else -> SingleColor(this.name, this.darkValue)
    }

    override fun toString(): String =
        "Color(name=${this.name}, lightValue=${this.lightValue}, darkValue=${this.darkValue})"
}
