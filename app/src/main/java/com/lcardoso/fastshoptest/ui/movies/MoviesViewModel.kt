package com.lcardoso.fastshoptest.ui.movies

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcardoso.fastshoptest.BaseViewModel
import com.lcardoso.fastshoptest.data.StateError
import com.lcardoso.fastshoptest.data.StateLoading
import com.lcardoso.fastshoptest.data.StateResponse
import com.lcardoso.fastshoptest.data.StateSuccess
import com.lcardoso.fastshoptest.data.model.MovieResponse
import com.lcardoso.fastshoptest.usecase.MovieUseCase
import com.lcardoso.fastshoptest.util.LANGUAGE
import com.lcardoso.fastshoptest.util.PAGE
import com.lcardoso.fastshoptest.util.TOKEN

class MoviesViewModel(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    val moviesResponse: LiveData<StateResponse<MovieResponse>> get() = _moviesResponse
    private val _moviesResponse = MutableLiveData<StateResponse<MovieResponse>>()

    @SuppressLint("CheckResult")
    fun getMovies(genreId: Int) {
        _moviesResponse.value = StateLoading()
        movieUseCase.execute(
            MovieUseCase.Params(
                genreId = genreId,
                apiKey = TOKEN,
                language = LANGUAGE,
                page = PAGE
            )
        ).subscribe(
            { movies -> _moviesResponse.value = StateSuccess(movies) },
            { e -> _moviesResponse.value = StateError(e) }
        ).let { disposables.add(it) }
    }
}