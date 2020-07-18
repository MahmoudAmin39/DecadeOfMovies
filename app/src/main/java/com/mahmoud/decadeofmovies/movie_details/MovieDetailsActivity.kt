package com.mahmoud.decadeofmovies.movie_details

import android.os.Bundle
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.ToolbarActivity

class MovieDetailsActivity : ToolbarActivity() {

    override fun shouldShowUpButton(): Boolean = true

    override fun getToolbarTitle(): String = "Movie details"

    override fun getViewResource(): Int = R.layout.activity_movie_details

    override fun afterViewInflation() {

    }
}