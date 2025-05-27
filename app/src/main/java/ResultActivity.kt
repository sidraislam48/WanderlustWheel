package com.example.wanderlustwheel

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name = intent.getStringExtra("name")
        val imageName = intent.getStringExtra("image")
        val description = intent.getStringExtra("description")

        val nameTextView = findViewById<TextView>(R.id.destinationName)
        val descriptionTextView = findViewById<TextView>(R.id.destinationDescription)
        val imageView = findViewById<ImageView>(R.id.destinationImage)

        nameTextView.text = name
        descriptionTextView.text = description

        // Load image by resource name
        val imageResId = resources.getIdentifier(imageName?.substringBeforeLast(".") ?: "", "drawable", packageName)
        if (imageResId != 0) {
            imageView.setImageResource(imageResId)
        } else {
            // Set default or placeholder image
            imageView.setImageResource(R.drawable.placeholder)
        }
    }
}
