package com.mahmoud.decadeofmovies.movie_details

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mahmoud.decadeofmovies.data.MoviesRepository
import com.mahmoud.decadeofmovies.data.model.Photo

class MovieDetailsViewModel : ViewModel() {

    fun getMoviePhotosUrls(movieTitle: String): LiveData<List<String>> {
        return Transformations.map(MoviesRepository.getMoviePhotos(movieTitle), Function {
            val photosUrls = mutableListOf<String>()
            if (it.isSuccess) {
                val photos = it.getOrNull()
                photos?.let {
                    for (photo in photos) {
                        val photoUrl = buildPhotoUrlFrom(photo)
                        photosUrls.add(photoUrl)
                    }
                }
            }
            return@Function photosUrls
        })
    }

    fun buildPhotoUrlFrom(photo: Photo) =
        "http://farm​${photo.farm}​.staticflickr.com/​${photo.server}​/​${photo.id}​_​${photo.secret}_z​.jpg"
}