package com.mahmoud.decadeofmovies.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhotosSearchApiRetrofitBuilder {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val photosSearchService by lazy {
        retrofit.create(PhotosSearchService::class.java)
    }
}