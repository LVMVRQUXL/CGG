package fr.esgi.rpa.cgg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myStartButton: Button? = findViewById(R.id.play_button)
        myStartButton?.setOnClickListener {
            val intent = Intent(this,QuestionActivity::class.java)
            startActivity(intent)

        }
    }


}