package com.mahmoud.decadeofmovies.movies_list

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.ToolbarActivity
import com.mahmoud.decadeofmovies.movie_details.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : ToolbarActivity() {

    override fun getViewResource(): Int = R.layout.activity_movies_list

    override fun afterViewInflation() {
        // Init ViewModel
        val viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        viewModel.getMovies()
    }
}