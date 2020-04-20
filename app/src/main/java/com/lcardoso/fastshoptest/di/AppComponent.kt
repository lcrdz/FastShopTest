package com.lcardoso.fastshoptest.di

import com.lcardoso.fastshoptest.api.MovieApi
import com.lcardoso.fastshoptest.api.RetrofitProvider
import com.lcardoso.fastshoptest.data.MovieRepository
import com.lcardoso.fastshoptest.ui.detail.MovieDetailViewModel
import com.lcardoso.fastshoptest.ui.home.HomeViewModel
import com.lcardoso.fastshoptest.ui.movies.MoviesViewModel
import com.lcardoso.fastshoptest.usecase.GenreUseCase
import com.lcardoso.fastshoptest.usecase.MovieDetailsUseCase
import com.lcardoso.fastshoptest.usecase.MovieUseCase
import com.lcardoso.fastshoptest.usecase.ReleaseMovieUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppComponent {
    val useCaseModule = module {
        single { GenreUseCase(get()) }
        single { ReleaseMovieUseCase(get()) }
        single { MovieUseCase(get()) }
        single { MovieDetailsUseCase(get()) }
    }

    val repository = module {
        single { MovieRepository(api = get()) }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get(), get()) }
        viewModel { MovieDetailViewModel(get()) }
        viewModel { MoviesViewModel(get()) }
    }

    val apiModule = module {
        fun provideRetrofit(): MovieApi {
            return RetrofitProvider.providesRetrofitApi(MovieApi::class.java)
        }
        single { provideRetrofit() }
    }
}