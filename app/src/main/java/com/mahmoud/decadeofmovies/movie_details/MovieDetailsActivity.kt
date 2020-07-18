package com.mahmoud.decadeofmovies.movie_details

import android.content.Context
import android.content.Intent
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.ToolbarActivity
import com.mahmoud.decadeofmovies.data.model.Movie
import com.mahmoud.decadeofmovies.movies_list.MovieViewHolder
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : ToolbarActivity() {

    companion object {

        private const val EXTRA_INTENT_MOVIE_ITEM = "EXTRA_INTENT_MOVIE_ITEM"

        fun getActivityIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_INTENT_MOVIE_ITEM, movie)
            return intent
        }
    }

    override fun shouldShowUpButton(): Boolean = true

    override fun getToolbarTitle(): String = "Movie details"

    override fun getViewResource(): Int = R.layout.activity_movie_details

    override fun afterViewInflation() {
        val movieItem = intent.getParcelableExtra<Movie>(EXTRA_INTENT_MOVIE_ITEM)
        movieItem?.let {
            // Show movie info here
            val movieInfoView = layoutInflater.inflate(R.layout.item_movie, view_movie_info, true)
            val movieInfoViewHolder = MovieViewHolder(movieInfoView)
            movieInfoViewHolder.bindMovie(it)
        }
    }
}