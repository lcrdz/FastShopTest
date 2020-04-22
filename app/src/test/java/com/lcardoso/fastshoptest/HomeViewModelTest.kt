package com.lcardoso.fastshoptest

import android.os.Build
import androidx.lifecycle.Observer
import com.lcardoso.fastshoptest.HomeViewModelRobot.act
import com.lcardoso.fastshoptest.HomeViewModelRobot.arrange
import com.lcardoso.fastshoptest.HomeViewModelRobot.assert
import com.lcardoso.fastshoptest.data.StateResponse
import com.lcardoso.fastshoptest.data.model.GenreResponse
import com.lcardoso.fastshoptest.ui.home.HomeViewModel
import com.lcardoso.fastshoptest.usecase.GenreUseCase
import com.lcardoso.fastshoptest.usecase.ReleaseMovieUseCase
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class HomeViewModelTest {
    private val genreUseCase = mockk<GenreUseCase>(relaxed = true)
    private val releaseMovieUseCase = mockk<ReleaseMovieUseCase>(relaxed = true)

    private val observer = mockk<Observer<StateResponse<GenreResponse>>>(relaxed = true)

    private val subject =
        HomeViewModel(genreUseCase = genreUseCase, releaseMovieUseCase = releaseMovieUseCase)

    @Test
    fun `when data is get with success`() {
        arrange {
            mockListGenreData()
            mockSuccesRequest(genreUseCase)
        }
        act {
            getGenreData(subject, observer)
        }
        assert {
            isStateSuccess(subject)
        }
    }
}