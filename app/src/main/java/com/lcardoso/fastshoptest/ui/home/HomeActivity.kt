package com.lcardoso.fastshoptest.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lcardoso.fastshoptest.R
import com.lcardoso.fastshoptest.data.StateError
import com.lcardoso.fastshoptest.data.StateLoading
import com.lcardoso.fastshoptest.data.StateResponse
import com.lcardoso.fastshoptest.data.StateSuccess
import com.lcardoso.fastshoptest.data.model.Genre
import com.lcardoso.fastshoptest.data.model.GenreResponse
import com.lcardoso.fastshoptest.data.model.MovieReleaseResponse
import com.lcardoso.fastshoptest.data.model.ResultRelease
import com.lcardoso.fastshoptest.ui.adapter.GenreAdapter
import com.lcardoso.fastshoptest.ui.adapter.MovieReleaseAdapter
import com.lcardoso.fastshoptest.ui.detail.MovieDetailActivity
import com.lcardoso.fastshoptest.ui.movies.MoviesActivity
import com.lcardoso.fastshoptest.util.nonNullObserve
import com.lcardoso.fastshoptest.util.visible
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupObservables()
        getData()
    }

    private fun getData() {
        viewModel.getReleaseMovies()
        viewModel.getGenres()
    }

    private fun setupObservables() {
        viewModel.releasesResponse.nonNullObserve(this) { state ->
            processRequest(state)
        }
        viewModel.genreResponse.nonNullObserve(this) { state ->
            processGenreRequest(state)
        }
    }

    private fun processRequest(state: StateResponse<MovieReleaseResponse>) = when (state) {
        is StateSuccess<MovieReleaseResponse> -> setupReleaseMovieAdapter(state.data.results)
        is StateError -> Toast.makeText(this, state.e.message, Toast.LENGTH_SHORT).show();
        is StateLoading -> pbMovies.visible(true)
    }

    private fun processGenreRequest(state: StateResponse<GenreResponse>) = when (state) {
        is StateSuccess<GenreResponse> -> setupGenreAdapter(state.data.genres)
        is StateError -> Toast.makeText(this, state.e.message, Toast.LENGTH_SHORT).show()
        is StateLoading -> Unit
    }

    private fun setupReleaseMovieAdapter(data: List<ResultRelease>) {
        pbMovies.visible(false)
        with(rvReleaseMovies) {
            layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = MovieReleaseAdapter(data) { movie ->
                startActivity<MovieDetailActivity>("MOVIE_ID" to movie.id)
            }
        }
    }

    private fun setupGenreAdapter(data: List<Genre>) {
        with(rvGenres) {
            layoutManager =
                GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = GenreAdapter(data) { genre ->
                startActivity<MoviesActivity>("GENRE_ID" to genre.id, "GENRE_NAME" to genre.name)
            }
        }
    }
}

