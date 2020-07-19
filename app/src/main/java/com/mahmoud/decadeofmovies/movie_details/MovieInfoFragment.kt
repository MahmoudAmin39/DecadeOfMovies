package com.mahmoud.decadeofmovies.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.data.model.Movie
import com.mahmoud.decadeofmovies.movie_details.MovieDetailsActivity.Companion.EXTRA_INTENT_MOVIE_ITEM
import com.mahmoud.decadeofmovies.movies_list.MovieViewHolder
import kotlinx.android.synthetic.main.fragment_movie_info.*

class MovieInfoFragment : Fragment() {

    private var movieItem: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieItem = it.getParcelable(EXTRA_INTENT_MOVIE_ITEM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(movie: Movie) =
            MovieInfoFragment().apply {
                arguments = Bundle().apply {
                    this.putParcelable(EXTRA_INTENT_MOVIE_ITEM, movie)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieItem?.let {
            // Show movie info here
            val movieInfoView = layoutInflater.inflate(R.layout.item_movie, view_movie_info, true)
            val movieInfoViewHolder = MovieViewHolder(movieInfoView)
            movieInfoViewHolder.bindMovie(it)

            // Show cast
            it.cast?.let {
                recyclerView_movie_cast.layoutManager = LinearLayoutManager(activity)
                recyclerView_movie_cast.isNestedScrollingEnabled = false
                recyclerView_movie_cast.adapter = TextAdapter(it)
            }

            // Show Genres
            it.genres?.let {
                recyclerView_movie_genres.layoutManager = LinearLayoutManager(activity)
                recyclerView_movie_genres.isNestedScrollingEnabled = false
                recyclerView_movie_genres.adapter = TextAdapter(it)
            }
        }
    }
}