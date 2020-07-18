package com.mahmoud.decadeofmovies

import android.app.Application
import android.content.Context

class MoviesApp: Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}