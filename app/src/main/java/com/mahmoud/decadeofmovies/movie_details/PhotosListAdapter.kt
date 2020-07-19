package com.mahmoud.decadeofmovies.movie_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.decadeofmovies.R
import kotlin.properties.Delegates

class PhotosListAdapter(itemWidthAndHeight: Int, photosUrls: List<String>) :
    RecyclerView.Adapter<PhotosListAdapter.PhotoViewHolder>() {

    var photosUrl: List<String> by Delegates.notNull()
    var itemWidthAndHeight: Int by Delegates.notNull()

    init {
        this.photosUrl = photosUrls
        this.itemWidthAndHeight = itemWidthAndHeight
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_photo, parent, false)
        val params = view.layoutParams
        params.width = itemWidthAndHeight
        params.height = itemWidthAndHeight
        view.layoutParams = params
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindPhoto("")
    }

    inner class PhotoViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bindPhoto(photoUrl: String) {
            // TODO: 7/19/20 Use Glide to show the photo
        }
    }
}
