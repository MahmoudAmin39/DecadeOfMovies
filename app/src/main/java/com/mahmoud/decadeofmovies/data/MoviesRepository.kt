package com.mahmoud.decadeofmovies.data

import com.mahmoud.decadeofmovies.data.model.Movie
import com.mahmoud.decadeofmovies.io.DiskUtils.getJsonStringFromFile
import org.json.JSONArray
import org.json.JSONObject

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
            this.movies = moviesArray
        }
        return movies as List<Movie>
    }
}