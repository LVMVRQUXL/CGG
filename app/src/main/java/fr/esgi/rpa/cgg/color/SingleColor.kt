package fr.esgi.rpa.cgg.color

class SingleColor(private val name: String, private val value: String) {
    fun getName(): String = this.name

    fun getValue(): String = this.value

    override fun equals(other: Any?): Boolean =
        other is SingleColor && this.name == other.name && this.value == other.value

    override fun hashCode(): Int = this.name.hashCode() + this.value.hashCode()

    override fun toString(): String = "SingleColor(name=${this.name}, value=${this.value})"
}