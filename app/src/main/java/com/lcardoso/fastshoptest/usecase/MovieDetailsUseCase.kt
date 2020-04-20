package com.lcardoso.fastshoptest.usecase

import com.lcardoso.fastshoptest.data.MovieRepository
import com.lcardoso.fastshoptest.data.model.MovieDetailResponse

class MovieDetailsUseCase(
    private val repository: MovieRepository
) : UseCase<MovieDetailResponse, MovieDetailsUseCase.Params> {

    override fun execute(params: Params) = repository
        .getMovieDetails(params.movieId, params.apiKey, params.language)

    data class Params(
        val movieId: Int,
        val apiKey: String,
        val language: String
    )
}