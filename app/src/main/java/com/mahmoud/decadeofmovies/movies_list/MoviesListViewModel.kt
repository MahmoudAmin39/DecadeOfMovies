package com.mahmoud.decadeofmovies.movies_list

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud.decadeofmovies.R
import com.mahmoud.decadeofmovies.data.MoviesRepository.getMoviesFromFile
import com.mahmoud.decadeofmovies.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesListViewModel : ViewModel() {

    private var moviesLiveData: MutableLiveData<List<Movie>>? = null
    private var loadingVisibilityLiveData: MutableLiveData<Int>? = null

    fun getMovies(): LiveData<List<Movie>>? {
        if (moviesLiveData == null) {
            moviesLiveData = MutableLiveData()
            loadingVisibilityLiveData?.value = VISIBLE
            viewModelScope.launch {
                val movies = withContext(Dispatchers.IO) {
                    getMoviesFromFile(R.raw.movies)
                }
                // Emit the result back to the activity
                loadingVisibilityLiveData?.value = GONE
                moviesLiveData?.value = movies
            }
        }
        return moviesLiveData
    }

    fun getLoadingVisibilityState(): LiveData<Int>? {
        if (loadingVisibilityLiveData == null) {
            loadingVisibilityLiveData = MutableLiveData()
        }
        return loadingVisibilityLiveData
    }
}