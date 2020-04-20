package com.lcardoso.fastshoptest.api

import com.lcardoso.fastshoptest.data.model.GenreResponse
import com.lcardoso.fastshoptest.data.model.MovieDetailResponse
import com.lcardoso.fastshoptest.data.model.MovieReleaseResponse
import com.lcardoso.fastshoptest.data.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<GenreResponse>

    @GET("movie/now_playing")
    fun getReleaseMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<MovieReleaseResponse>

    @GET("movie/{genre_id}/lists")
    fun getMovies(
        @Path("genre_id") genreId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<MovieDetailResponse>
}