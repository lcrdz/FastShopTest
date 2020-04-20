package com.lcardoso.fastshoptest.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcardoso.fastshoptest.BaseViewModel
import com.lcardoso.fastshoptest.data.StateError
import com.lcardoso.fastshoptest.data.StateLoading
import com.lcardoso.fastshoptest.data.StateResponse
import com.lcardoso.fastshoptest.data.StateSuccess
import com.lcardoso.fastshoptest.data.model.MovieDetailResponse
import com.lcardoso.fastshoptest.usecase.MovieDetailsUseCase
import com.lcardoso.fastshoptest.util.LANGUAGE
import com.lcardoso.fastshoptest.util.TOKEN

class MovieDetailViewModel(
    private val movieDetailsUseCase: MovieDetailsUseCase
) : BaseViewModel() {

    val detailResponse: LiveData<StateResponse<MovieDetailResponse>> get() = _detailResponse
    private val _detailResponse = MutableLiveData<StateResponse<MovieDetailResponse>>()

    @SuppressLint("CheckResult")
    fun getMovieDetails(movieId: Int) {
        _detailResponse.value = StateLoading()
        movieDetailsUseCase.execute(
            MovieDetailsUseCase.Params(
                movieId = movieId,
                apiKey = TOKEN,
                language = LANGUAGE
            )
        ).subscribe(
            { details -> _detailResponse.value = StateSuccess(details) },
            { e -> _detailResponse.value = StateError(e) }
        ).let { disposables.add(it) }
    }
}
