package com.lcardoso.fastshoptest.data

import com.lcardoso.fastshoptest.api.MovieApi
import com.lcardoso.fastshoptest.api.doRequest

class MovieRepository(
    private val api: MovieApi
) {
    fun getGenres(apiKey: String, language: String) = doRequest {
        api.getGenres(apiKey, language)
    }

    fun getReleaseMovies(apiKey: String, language: String, page: Int) = doRequest {
        api.getReleaseMovies(apiKey, language, page)
    }

    fun getMovies(genreId: Int, apiKey: String, language: String, page: Int) = doRequest {
        api.getMovies(genreId, apiKey, language, page)
    }

    fun getMovieDetails(movieId: Int, apiKey: String, language: String) = doRequest {
        api.getMovieDetails(movieId, apiKey, language)
    }
}