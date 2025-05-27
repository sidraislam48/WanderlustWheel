package com.example.wanderlustwheel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val startButton = findViewById<Button>(R.id.btnStart)
        val instructionsButton = findViewById<Button>(R.id.btnInstructions)

        startButton.setOnClickListener {
            // Navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        instructionsButton.setOnClickListener {
            showInstructionsPopup()
        }
    }

    private fun showInstructionsPopup() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.popup_instructions, null)
        val slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        dialogView.startAnimation(slideDown)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialogView.findViewById<TextView>(R.id.gotItText).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
