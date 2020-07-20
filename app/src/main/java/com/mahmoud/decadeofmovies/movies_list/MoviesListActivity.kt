package com.mahmoud.decadeofmovies.movies_list

import android.app.Activity
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.ToolbarActivity
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : ToolbarActivity(), SearchView.OnQueryTextListener {

    var moviesListAdapter: MoviesListAdapter? = null

    private val viewModel by lazy {
        ViewModelProvider(this).get(MoviesListViewModel::class.java)
    }

    override fun getViewResource(): Int = R.layout.activity_movies_list

    override fun afterViewInflation() {
        // Setup the RecyclerView
        recyclerView_movies_list.layoutManager = LinearLayoutManager(this)
        moviesListAdapter = MoviesListAdapter()
        recyclerView_movies_list.adapter = moviesListAdapter

        viewModel.getLoadingVisibilityState()?.observe(this, Observer {
            progressBar.visibility = it
        })
        viewModel.getMovies()?.observe(this,
            Observer {
                moviesListAdapter?.addMovies(it)
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.item_search)
        val searchView =
            searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                searchView.setQuery("", true)
                hideKeyboard()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_search && item.actionView is SearchView) {
            (item.actionView as SearchView).onActionViewExpanded()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(s: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String?): Boolean {
        viewModel.filterMovies(s)
        return true
    }

    fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}