package com.mahmoud.decadeofmovies.data.model

import org.json.JSONArray
import org.json.JSONObject

data class Movie(
    val id: Int, val title: String?, val year: Int?,
    val rating: Int?, val cast: MutableList<String>?, val genres: MutableList<String>?) {

    companion object {

        val TITLE = "title"
        val YEAR = "year"
        val RATING = "rating"
        val CAST = "cast"
        val GENRES = "genres"

        fun fromJson(id: Int, jsonString: JSONObject): Movie {
            var title: String? = null
            var year: Int? = null
            var rating: Int? = null
            val cast = mutableListOf<String>()
            val genres = mutableListOf<String>()

            if (jsonString.has(TITLE)) {
                title = jsonString.get(TITLE) as? String
            }

            if (jsonString.has(YEAR)) {
                year = jsonString.get(YEAR) as? Int
            }

            if (jsonString.has(RATING)) {
                rating = jsonString.get(RATING) as? Int
            }

            if (jsonString.has(CAST)) {
                val castJsonArray = jsonString.get(CAST) as JSONArray
                for (index in 0 until castJsonArray.length()) {
                    cast.add(castJsonArray.get(index) as String)
                }
            }

            if (jsonString.has(GENRES)) {
                val genresJsonArray = jsonString.get(GENRES) as JSONArray
                for (index in 0 until genresJsonArray.length()) {
                    genres.add(genresJsonArray.get(index) as String)
                }
            }

            return Movie(id, title, year, rating, cast, genres)
        }
    }
}