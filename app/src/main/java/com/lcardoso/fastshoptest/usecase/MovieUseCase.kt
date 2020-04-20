package com.lcardoso.fastshoptest.usecase

import com.lcardoso.fastshoptest.data.MovieRepository
import com.lcardoso.fastshoptest.data.model.MovieResponse

class MovieUseCase(
    private val repository: MovieRepository
) : UseCase<MovieResponse, MovieUseCase.Params> {

    override fun execute(params: Params) = repository
        .getMovies(params.genreId, params.apiKey, params.language, params.page)

    data class Params(
        val genreId: Int,
        val apiKey: String,
        val language: String,
        val page: Int
    )
}