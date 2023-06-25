package com.example.notes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val videoV=findViewById<VideoView>(R.id.videoView)
        videoV.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.notebook));
        videoV.start()
supportActionBar?.hide()

        Handler().postDelayed({val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()},2000)
    }
}