package com.lcardoso.fastshoptest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lcardoso.fastshoptest.R
import com.lcardoso.fastshoptest.data.model.Genre
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreAdapter(
    private val genres: List<Genre>,
    private val onClickListener: (genre: Genre) -> Unit
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        return GenreViewHolder(view)
    }

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bindView(genres[position])
    }

    inner class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvGenre = view.tvGenre

        fun bindView(genre: Genre) {
            tvGenre.text = genre.name

            itemView.setOnClickListener {
                onClickListener.invoke(genre)
            }
        }

    }
}