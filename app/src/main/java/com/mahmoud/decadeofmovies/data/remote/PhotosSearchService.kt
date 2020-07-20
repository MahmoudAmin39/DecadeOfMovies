package com.mahmoud.decadeofmovies.data.remote

import com.mahmoud.decadeofmovies.data.model.FlickrResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosSearchService {

    //http​s:​//api.flickr.​com​/services/rest/?method=flickr.photos.​search​&api_key={ YOUR_API_KEY}
    // &format=json&nojsoncallback=​1​&text={MOVIE_TITLE}&page=​1​&per_page=​10

    @GET("services/rest/")
    fun getPhotos(@Query("text") searchTerm: String,
                  @Query("method") method: String = "flickr.photos.search",
                  @Query("api_key") apiKey: String = "ad2a61b38693eb1ae3bb3ec058de512b",
                  @Query("format") format: String = "json",
                  @Query("nojsoncallback") noJsonCallback: Int = 1,
                  @Query("page") page: Int = 1,
                  @Query("per_page") perPage: Int = 1) : Call<FlickrResponse>
}