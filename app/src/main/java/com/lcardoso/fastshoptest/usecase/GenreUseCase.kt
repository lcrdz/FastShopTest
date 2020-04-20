package com.lcardoso.fastshoptest.usecase

import com.lcardoso.fastshoptest.data.MovieRepository
import com.lcardoso.fastshoptest.data.model.GenreResponse

class GenreUseCase(
    private val repository: MovieRepository
) : UseCase<GenreResponse, GenreUseCase.Params> {

    override fun execute(params: Params) = repository
        .getGenres(params.apiKey, params.language)

    data class Params(
        val apiKey: String,
        val language: String
    )
}