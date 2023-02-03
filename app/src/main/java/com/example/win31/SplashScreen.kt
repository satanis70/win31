package com.example.win31

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

class SplashScreen : AppCompatActivity() {
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val layout = findViewById<ConstraintLayout>(R.id.activity_splash_layout)
        Glide.with(this).load("http://49.12.202.175/win31/phoneimg.png")
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    layout.background = resource

                    handler.postDelayed({
                        startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                        finish()
                    }, 3000)
                }
            })
    }
}