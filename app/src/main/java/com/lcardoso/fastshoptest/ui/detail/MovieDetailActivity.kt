package com.lcardoso.fastshoptest.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lcardoso.fastshoptest.R
import com.lcardoso.fastshoptest.data.StateError
import com.lcardoso.fastshoptest.data.StateLoading
import com.lcardoso.fastshoptest.data.StateResponse
import com.lcardoso.fastshoptest.data.StateSuccess
import com.lcardoso.fastshoptest.data.model.MovieDetailResponse
import com.lcardoso.fastshoptest.util.IMAGE_URL
import com.lcardoso.fastshoptest.util.nonNullObserve
import com.lcardoso.fastshoptest.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId: Int = intent.getIntExtra("MOVIE_ID", 0)

        setupObservable()
        viewModel.getMovieDetails(movieId)
    }

    private fun setupObservable() {
        viewModel.detailResponse.nonNullObserve(this) { state ->
            processRequest(state)
        }
    }

    private fun processRequest(state: StateResponse<MovieDetailResponse>) = when (state) {
        is StateSuccess<MovieDetailResponse> -> initViews(state.data)
        is StateError -> showError()
        is StateLoading -> pbMovies.visible(true)
    }

    private fun showError() {
        pbMovies.visible(false)
        ivBack.setOnClickListener { onBackPressed() }
        tvMovieVote.visible(false)
        ivError.visible(true)
    }

    private fun initViews(movie: MovieDetailResponse) {
        pbMovies.visible(false)

        Picasso.get().load(IMAGE_URL + movie.backdropPath).placeholder(R.color.colorPrimary)
            .into(ivMovie)
        tvMovieTitle.text = movie.title
        tvMovieOverview.text = movie.overview
        tvMovieVote.text = movie.voteAverage.toString()
        tvMovieDate.text = movie.releaseDate
        ivBack.setOnClickListener { onBackPressed() }
    }
}
