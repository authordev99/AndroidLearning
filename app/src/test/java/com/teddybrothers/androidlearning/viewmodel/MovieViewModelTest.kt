package com.teddybrothers.androidlearning.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.teddybrothers.androidlearning.utils.appModule
import com.teddybrothers.androidlearning.view.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MovieViewModelTest : KoinTest {

    private val invalidMovieId = "1"
    private val validMovieId = "136368"

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val movieViewModel: MovieViewModel by inject()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(appModule) }
    }

    @Test
    fun test_getMovieList() {
        movieViewModel.getMoviesRepository(MainActivity.RELEASE_DATE_DESC,1)
        Thread.sleep(3000)

        assert(movieViewModel.listOfMovies.value != null)

    }

    @Test
    fun test_getInvalidMovieList() {
        movieViewModel.getMoviesRepository("desc",0)
        Thread.sleep(3000)

        assert(movieViewModel.listOfMovies.value == null)

    }

    @Test
    fun test_getMovieDetails() {
        movieViewModel.getMovieDetailRepository(validMovieId)
        Thread.sleep(3000)

        assert(movieViewModel.movieDetail.value != null)

    }

    @Test
    fun test_getInvalidMovieDetails() {
        movieViewModel.getMovieDetailRepository(invalidMovieId)
        Thread.sleep(3000)

        assert(movieViewModel.movieDetail.value == null)

    }

    @After
    fun after() {
        stopKoin()
    }

}