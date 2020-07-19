package com.mahmoud.decadeofmovies.movies_list

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.ToolbarActivity
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : ToolbarActivity() {

    var moviesListAdapter: MoviesListAdapter? = null

    override fun getViewResource(): Int = R.layout.activity_movies_list

    override fun afterViewInflation() {
        // Setup the RecyclerView
        recyclerView_movies_list.layoutManager = LinearLayoutManager(this)
        moviesListAdapter = MoviesListAdapter()
        recyclerView_movies_list.adapter = moviesListAdapter

        // Init ViewModel
        val viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        viewModel.getLoadingVisibilityState()?.observe(this, Observer {
            progressBar.visibility = it
        })
        viewModel.getMovies()?.observe(this,
            Observer {
                moviesListAdapter?.addMovies(it)
            })
    }
}