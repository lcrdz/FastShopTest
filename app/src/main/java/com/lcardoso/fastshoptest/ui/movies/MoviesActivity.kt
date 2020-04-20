package com.lcardoso.fastshoptest.ui.movies

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.lcardoso.fastshoptest.R
import com.lcardoso.fastshoptest.data.StateError
import com.lcardoso.fastshoptest.data.StateLoading
import com.lcardoso.fastshoptest.data.StateResponse
import com.lcardoso.fastshoptest.data.StateSuccess
import com.lcardoso.fastshoptest.data.model.MovieDetailResponse
import com.lcardoso.fastshoptest.data.model.MovieResponse
import com.lcardoso.fastshoptest.data.model.Result
import com.lcardoso.fastshoptest.ui.adapter.GenreAdapter
import com.lcardoso.fastshoptest.ui.adapter.MoviesAdapter
import com.lcardoso.fastshoptest.ui.detail.MovieDetailActivity
import com.lcardoso.fastshoptest.util.nonNullObserve
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_movies.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val genreId: Int = intent.getIntExtra("GENRE_ID", 0)
        val genreName: String? = intent.getStringExtra("GENRE_NAME")

        initViews(genreName)
        setupObservable()
        viewModel.getMovies(genreId)
    }

    private fun initViews(name: String?) {
        ivBack.setOnClickListener { onBackPressed() }
        name?.let {
            tvGenreName.text = it
        }
    }

    private fun setupObservable() {
        viewModel.moviesResponse.nonNullObserve(this) { state ->
            processRequest(state)
        }
    }

    private fun processRequest(state: StateResponse<MovieResponse>) = when (state) {
        is StateSuccess<MovieResponse> -> setupAdapter(state.data.results)
        is StateError -> Unit
        is StateLoading -> Unit
    }

    private fun setupAdapter(movies: List<Result>) {
        with(rvMovies) {
            layoutManager =
                GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = MoviesAdapter(movies) {movie ->
                startActivity<MovieDetailActivity>("MOVIE_ID" to movie.id)
            }
        }
    }
}
