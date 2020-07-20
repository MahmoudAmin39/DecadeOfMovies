package com.mahmoud.decadeofmovies.io

import com.mahmoud.decadeofmovies.MoviesApp
import com.mahmoud.decadeofmovies.R

object DiskUtils {

    fun getJsonStringFromFile(fileRes: Int): String? {
        val fis = MoviesApp.context?.resources?.openRawResource(R.raw.movies)
        fis?.apply {
            // Read the stream
            val bytes = ByteArray(available())
            read(bytes)
            // Close the stream
            close()
            return String(bytes)
        }
        return null
    }
}