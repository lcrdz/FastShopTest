package com.lcardoso.fastshoptest

import androidx.lifecycle.Observer
import com.lcardoso.fastshoptest.data.StateResponse
import com.lcardoso.fastshoptest.data.StateSuccess
import com.lcardoso.fastshoptest.data.model.Genre
import com.lcardoso.fastshoptest.data.model.GenreResponse
import com.lcardoso.fastshoptest.ui.home.HomeViewModel
import com.lcardoso.fastshoptest.usecase.GenreUseCase
import io.mockk.every
import io.reactivex.Observable
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat

object HomeViewModelRobot {

    class HomeViewModelRobotArrange {
        fun mockListGenreData(): GenreResponse =
            GenreResponse(
                listOf(
                    Genre(id = 1, name = "Aventura")
                )
            )

        fun mockSuccesRequest(
            genreUseCase: GenreUseCase
        ) {
            every {
                genreUseCase.execute(any())
            } returns Observable.just(mockListGenreData())
        }
    }

    class HomeViewModelRobotAct {
        fun getGenreData(
            subject: HomeViewModel,
            observer: Observer<StateResponse<GenreResponse>>
        ) {
            subject.genreResponse.observeForever(observer)
            subject.getGenres()
            subject.genreResponse.removeObserver(observer)
        }
    }

    class HomeViewModelRobotAssert {
        fun isStateSuccess(subject: HomeViewModel) {
            subject.genreResponse.value.assertInstanceOf(StateSuccess::class.java)
        }
    }

    fun arrange(func: HomeViewModelRobotArrange.() -> Unit) =
        HomeViewModelRobotArrange().apply(func)

    fun act(func: HomeViewModelRobotAct.() -> Unit) =
        HomeViewModelRobotAct().apply(func)

    fun assert(func: HomeViewModelRobotAssert.() -> Unit) =
        HomeViewModelRobotAssert().apply(func)

    infix fun <T> T.assertInstanceOf(clazz: Class<*>) {
        assertThat(this, CoreMatchers.instanceOf(clazz))
    }
}