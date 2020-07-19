package com.mahmoud.decadeofmovies.movies_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.data.model.Movie
import com.mahmoud.decadeofmovies.movie_details.MovieDetailsActivity

class MoviesListAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener {
            val intent = MovieDetailsActivity.getActivityIntent(holder.itemView.context, movie)
            holder.itemView.context.startActivity(intent)
        }
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}