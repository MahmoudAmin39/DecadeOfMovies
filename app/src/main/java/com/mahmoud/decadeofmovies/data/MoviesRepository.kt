package com.mahmoud.decadeofmovies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahmoud.decadeofmovies.data.model.FlickrResponse
import com.mahmoud.decadeofmovies.data.model.Movie
import com.mahmoud.decadeofmovies.data.model.Photo
import com.mahmoud.decadeofmovies.data.remote.PhotosSearchApiRetrofitBuilder
import com.mahmoud.decadeofmovies.io.DiskUtils.getJsonStringFromFile
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

object MoviesRepository {

    var movies: List<Movie>? = null

    fun getMoviesFromFile(fileRes: Int): List<Movie> {
        if (movies == null) {
            val moviesArray = mutableListOf<Movie>()
            val jsonString = getJsonStringFromFile(fileRes)
            jsonString?.let {
                val fullJson = JSONObject(it)
                val moviesJson = fullJson.get("movies")
                for (index in 0 until (moviesJson as JSONArray).length()) {
                    val movieJson = moviesJson.getJSONObject(index)
                    val movie = Movie.fromJson(index, movieJson)
                    moviesArray.add(movie)
                }
            }
            moviesArray.sort()
            this.movies = moviesArray
        }
        return movies as List<Movie>
    }

    // This method filters the movie title, cast and genres
    fun filterMovies(searchTerm: String) : List<Movie> {
        val filteredList = mutableListOf<Movie>()
        movies?.filter { movie ->
            val movieTermsList = mutableListOf<String>()
            movie.title?.let { title -> movieTermsList.add(title) }
            movie.cast?.let { cast -> cast.forEach { castName -> movieTermsList.add(castName) } }
            movie.genres?.let { genres -> genres.forEach { genre -> movieTermsList.add(genre) } }
            movieTermsList.forEach { term -> if (term.contains(searchTerm, true)) return@filter true }
            return@filter false
        }?.forEach { movie ->
            if (filteredList.filter {
                    if (movie.year != null && it.year != null) { return@filter movie.year == it.year }
                    return@filter false
                }.size < 5) {
                filteredList.add(movie)
            }
        }

        return filteredList
    }

    fun getMoviePhotos(movieTitle: String): LiveData<Result<List<Photo>>> {
        val responseLiveData = MutableLiveData<Result<List<Photo>>>()
        PhotosSearchApiRetrofitBuilder.photosSearchService.getPhotos(movieTitle)
            .enqueue(object : retrofit2.Callback<FlickrResponse> {

                override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                    responseLiveData.value = Result.failure(t)
                }

                override fun onResponse(
                    call: Call<FlickrResponse>,
                    response: Response<FlickrResponse>
                ) {
                    response.body()?.photos?.photos?.let {
                        responseLiveData.value = Result.success(it)
                    }
                }
            })
        return responseLiveData
    }
}