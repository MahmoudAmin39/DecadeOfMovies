package com.mahmoud.decadeofmovies.movie_details

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.data.model.Movie
import com.mahmoud.decadeofmovies.movie_details.MovieDetailsActivity.Companion.EXTRA_INTENT_MOVIE_ITEM
import kotlinx.android.synthetic.main.fragment_movie_photos.*

class MoviePhotosFragment : Fragment() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_photos, container, false)
    }

    companion object {

        private const val ITEM_PHOTO_MARGIN = 8
        private const val SPAN_COUNT = 2

        @JvmStatic
        fun newInstance(movie: Movie) =
            MoviePhotosFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_INTENT_MOVIE_ITEM, movie)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieItem?.let {
            // Show Photos
            val displayMetrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            val screenWidthInPx = displayMetrics.widthPixels
            val photoItemWidthInPx =
                (screenWidthInPx - convertDpToPixel(4 * ITEM_PHOTO_MARGIN)) / SPAN_COUNT

            it.title?.let {
                progressBar_movie_photos.visibility = View.VISIBLE
                val viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
                viewModel.getMoviePhotosUrls(it).observe(this, Observer {
                    progressBar_movie_photos.visibility = View.GONE

                    when (it.isSuccess) {
                        true -> {
                            recyclerView_movie_photos.layoutManager =
                                GridLayoutManager(activity, SPAN_COUNT)
                            recyclerView_movie_photos.isNestedScrollingEnabled = false
                            val photosAdapter = PhotosListAdapter(
                                photoItemWidthInPx, it.getOrDefault(
                                    emptyList()
                                )
                            )
                            recyclerView_movie_photos.adapter = photosAdapter
                        }

                        false -> {
                            // TODO: 7/19/20 Handle Error while loading photos
                        }
                    }
                })
            }
        }
    }

    private fun convertDpToPixel(dp: Int) =
        dp * resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
}