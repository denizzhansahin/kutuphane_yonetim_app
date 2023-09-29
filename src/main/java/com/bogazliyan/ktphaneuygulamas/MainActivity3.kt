package com.bogazliyan.ktphaneuygulamas

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val button2 = findViewById<Button>(R.id.button)
        button2.setOnClickListener {
            val url = "https://www.denizhansahin.com/"

            val defaultBrowserIntent = Intent(Intent.ACTION_VIEW)
            defaultBrowserIntent.data = Uri.parse(url)
            startActivity(defaultBrowserIntent)
        }

        val button = findViewById<Button>(R.id.button4)
        button.setOnClickListener {
            val baslat_intent = Intent(this, GizlilikPolitikasi::class.java)
            startActivity(baslat_intent)
        }

    }
}