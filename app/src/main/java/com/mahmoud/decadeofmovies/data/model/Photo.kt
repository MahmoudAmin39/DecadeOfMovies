package com.mahmoud.decadeofmovies.data.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") val id: String?,
    @SerializedName("secret") val secret: String?,
    @SerializedName("server") val server: String?,
    @SerializedName("farm") val farm: Int?
)

data class Photos(@SerializedName("photo") val photos: List<Photo>)

data class FlickrResponse(@SerializedName("photos") val photos: Photos)