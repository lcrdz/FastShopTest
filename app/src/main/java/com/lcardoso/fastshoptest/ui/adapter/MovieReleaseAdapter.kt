package com.lcardoso.fastshoptest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lcardoso.fastshoptest.R
import com.lcardoso.fastshoptest.data.model.ResultRelease
import com.lcardoso.fastshoptest.util.IMAGE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieReleaseAdapter(
    private val movies: List<ResultRelease>,
    private val onClickListener: (movie: ResultRelease) -> Unit
) : RecyclerView.Adapter<MovieReleaseAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieReleaseAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieReleaseAdapter.MovieViewHolder, position: Int) {
        holder.bindView(movies[position])
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val posterMovie = view.ivPosterMovie
        private val titleMovie = view.tvTitleMovie

        fun bindView(movie: ResultRelease) {

            Picasso.get().load(IMAGE_URL + movie.posterPath).placeholder(R.color.colorPrimary)
                .into(posterMovie)
            if (movie.title.length > 15) {
                titleMovie.text = ("${movie.title.subSequence(0, 15)}...")
            } else {
                titleMovie.text = movie.title
            }

            itemView.setOnClickListener {
                onClickListener.invoke(movie)
            }
        }
    }
}