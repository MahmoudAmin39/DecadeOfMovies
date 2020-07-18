package com.mahmoud.decadeofmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mahmoud.decadeofmovies.movies_list.MoviesListActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            val intent = Intent(this, MoviesListActivity::class.java)
            startActivity(intent)
        }, 1000)
        setContentView(R.layout.activity_main)
    }
}