package com.mahmoud.decadeofmovies.movie_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.decadeofmovies.R
import kotlinx.android.synthetic.main.item_text.view.*
import java.util.*
import kotlin.properties.Delegates

class TextAdapter(listItems: List<String>): RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    private var items: List<String> by Delegates.notNull()

    init {
        this.items = listItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        return TextViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bindText(items[position])
    }

    inner class TextViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bindText(text: String) {
            itemView.textView_text_initials.text = getInitials(text)
            itemView.textView_text.text = text
        }

        private fun getInitials(text: String) : String {
            val textSplitted: List<String> = text.split(" ")
            return when(textSplitted.size) {
                1 -> String.format(
                    Locale.getDefault(),
                    "%s",
                    textSplitted[0][0]
                )

                else -> {
                    String.format(
                        Locale.getDefault(),"%s%s",textSplitted[0][0], textSplitted[1][0])
                }
            }
        }
    }
}