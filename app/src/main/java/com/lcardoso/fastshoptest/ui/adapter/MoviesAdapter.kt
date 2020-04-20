package com.lcardoso.fastshoptest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lcardoso.fastshoptest.R
import com.lcardoso.fastshoptest.data.model.Result
import com.lcardoso.fastshoptest.util.IMAGE_URL
import com.lcardoso.fastshoptest.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_genre_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.ivPosterMovie
import kotlinx.android.synthetic.main.movie_item.view.tvTitleMovie

class MoviesAdapter(
    private val movies: List<Result>,
    private val onClickListener: (movie: Result) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_genre_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        holder.bindView(movies[position])
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val posterMovie = view.ivPosterMovie
        private val titleMovie = view.tvTitleMovie
        private val notImage = view.ivNotImage

        fun bindView(movie: Result) {

            if (movie.posterPath.isNullOrBlank()) {
                notImage.visible(true)
            }

            Picasso.get().load(IMAGE_URL + movie.posterPath).placeholder(R.color.colorPrimary)
                .into(posterMovie)
            if (movie.name.length > 15) {
                titleMovie.text = ("${movie.name.subSequence(0, 15)}...")
            } else {
                titleMovie.text = movie.name
            }

            itemView.setOnClickListener {
                onClickListener.invoke(movie)
            }
        }
    }
}