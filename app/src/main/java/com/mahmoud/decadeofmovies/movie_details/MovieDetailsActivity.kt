package com.mahmoud.decadeofmovies.movie_details

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.ToolbarActivity
import com.mahmoud.decadeofmovies.data.model.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlin.properties.Delegates

class MovieDetailsActivity : ToolbarActivity() {

    companion object {

        const val EXTRA_INTENT_MOVIE_ITEM = "EXTRA_INTENT_MOVIE_ITEM"

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
            pager_movie_details.adapter = FragmentsAdapter(this, it)
            TabLayoutMediator(tabLayout_movie_details, pager_movie_details) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Movie info"
                        tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_info_24dp)
                    }

                    1 -> {
                        tab.text = "Movie Photos"
                        tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_photo_24dp)
                    }
                }
            }.attach()
        }
    }

    inner class FragmentsAdapter(activity: FragmentActivity, movie: Movie) :
        FragmentStateAdapter(activity) {

        private var movie: Movie by Delegates.notNull()

        init {
            this.movie = movie
        }

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> MovieInfoFragment.newInstance(movie)
            1 -> MoviePhotosFragment.newInstance(movie)
            else -> Fragment()
        }
    }
}