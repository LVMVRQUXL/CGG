package fr.esgi.rpa.cgg.color

import android.os.CountDownTimer

private const val INTERVAL: Long = 1000
private const val START: Long = 3000

class ColorTimer(private val updateBackground: () -> Unit) : CountDownTimer(START, INTERVAL) {
    override fun onTick(millisUntilFinished: Long) {}

    override fun onFinish() = this.updateBackground()
}