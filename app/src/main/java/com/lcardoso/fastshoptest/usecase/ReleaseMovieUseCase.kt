package com.lcardoso.fastshoptest.usecase

import com.lcardoso.fastshoptest.data.MovieRepository
import com.lcardoso.fastshoptest.data.model.MovieReleaseResponse

class ReleaseMovieUseCase(
    private val repository: MovieRepository
) : UseCase<MovieReleaseResponse, ReleaseMovieUseCase.Params> {

    override fun execute(params: Params) = repository
        .getReleaseMovies(params.apiKey, params.language, params.page)

    data class Params(
        val apiKey: String,
        val language: String,
        val page: Int
    )
}