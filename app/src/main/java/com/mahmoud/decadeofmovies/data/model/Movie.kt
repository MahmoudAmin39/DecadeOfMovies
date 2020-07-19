package com.mahmoud.decadeofmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONObject

@Parcelize
data class Movie(
    val id: Int, val title: String?, val year: Int?,
    val rating: Int?, val cast: List<String>?, val genres: List<String>?
) : Parcelable, Comparable<Movie> {

    companion object {

        private const val TITLE = "title"
        private const val YEAR = "year"
        private const val RATING = "rating"
        private const val CAST = "cast"
        private const val GENRES = "genres"

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

    override fun compareTo(other: Movie): Int {
        // Compare by year (ASC)
        if (this.year != null && other.year != null) {
            val yearCompareResult = this.year.compareTo(other.year)
            if (yearCompareResult != 0) {
                return yearCompareResult
            } else {
                // They are both from the same year, Compare the rating (DESC)
                if (this.rating != null && other.rating != null) {
                    return other.rating.compareTo(this.rating)
                }
            }
        }
        return 0
    }
}