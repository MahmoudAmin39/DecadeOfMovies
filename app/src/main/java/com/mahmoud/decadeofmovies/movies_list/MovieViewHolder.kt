package com.mahmoud.decadeofmovies.movies_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.decadeofmovies.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(v: View): RecyclerView.ViewHolder(v) {

    fun bindMovie(movie: Movie) {
        itemView.textView_movie_title.text = movie.title
        itemView.textView_movie_year.text = movie.year.toString()
        movie.rating?.toFloat()?.let { itemView.ratingBar_movie_rating.rating = it }
    }
}