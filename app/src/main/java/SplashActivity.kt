package com.example.wanderlustwheel

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        val logo = findViewById<ImageView>(R.id.logoImage)
        val animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation)
        logo.startAnimation(animation)

        Handler().postDelayed({
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
