package com.nada.miniproject.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var logo:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        logo = findViewById(R.id.logo_splash_screen)
        logo.alpha = 0f
        logo.animate().setDuration(2000).alpha(1f).withEndAction{
            val intent = Intent(this, SliderActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}