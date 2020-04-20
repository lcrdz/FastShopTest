package com.lcardoso.fastshoptest.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcardoso.fastshoptest.BaseViewModel
import com.lcardoso.fastshoptest.data.StateError
import com.lcardoso.fastshoptest.data.StateLoading
import com.lcardoso.fastshoptest.data.StateResponse
import com.lcardoso.fastshoptest.data.StateSuccess
import com.lcardoso.fastshoptest.data.model.GenreResponse
import com.lcardoso.fastshoptest.data.model.MovieReleaseResponse
import com.lcardoso.fastshoptest.usecase.GenreUseCase
import com.lcardoso.fastshoptest.usecase.ReleaseMovieUseCase
import com.lcardoso.fastshoptest.util.LANGUAGE
import com.lcardoso.fastshoptest.util.PAGE
import com.lcardoso.fastshoptest.util.TOKEN

class HomeViewModel(
    private val releaseMovieUseCase: ReleaseMovieUseCase,
    private val genreUseCase: GenreUseCase
) : BaseViewModel() {

    val releasesResponse: LiveData<StateResponse<MovieReleaseResponse>> get() = _releasesResponse
    private val _releasesResponse = MutableLiveData<StateResponse<MovieReleaseResponse>>()

    val genreResponse: LiveData<StateResponse<GenreResponse>> get() = _genreResponse
    private val _genreResponse = MutableLiveData<StateResponse<GenreResponse>>()

    @SuppressLint("CheckResult")
    fun getReleaseMovies() {
        _releasesResponse.value = StateLoading()
        releaseMovieUseCase.execute(
            ReleaseMovieUseCase.Params(
                apiKey = TOKEN,
                language = LANGUAGE,
                page = PAGE
            )
        ).subscribe(
            { movies -> _releasesResponse.value = StateSuccess(movies) },
            { e -> _releasesResponse.value = StateError(e) }
        ).let {
            disposables.add(it)
        }
    }

    @SuppressLint("CheckResult")
    fun getGenres() {
        _genreResponse.value = StateLoading()
        genreUseCase.execute(
            GenreUseCase.Params(
                apiKey = TOKEN,
                language = LANGUAGE
            )
        ).subscribe(
            { genres -> _genreResponse.value = StateSuccess(genres) },
            { e -> _genreResponse.value = StateError(e) }
        ).let {
            disposables.add(it)
        }
    }
}