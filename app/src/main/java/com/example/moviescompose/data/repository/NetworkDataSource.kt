package com.example.moviescompose.data.repository

import com.example.moviescompose.data.model.MovieResponse
import com.example.moviescompose.data.service.MoviesApi
import com.example.moviescompose.di.Dispatcher
import com.example.moviescompose.di.MoviesDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    @Dispatcher(MoviesDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val movieApi: MoviesApi
) {

    suspend fun getMovies(): Response<MovieResponse> =
        withContext(ioDispatcher) {
            movieApi.fetchMovies()
        }
}