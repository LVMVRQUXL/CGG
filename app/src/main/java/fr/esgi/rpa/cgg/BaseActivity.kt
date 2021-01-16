package fr.esgi.rpa.cgg

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract fun continueOnCreate()
}