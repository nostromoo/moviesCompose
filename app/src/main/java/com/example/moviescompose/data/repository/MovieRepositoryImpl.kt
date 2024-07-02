package com.example.moviescompose.data.repository

import com.example.moviescompose.data.model.MovieResponse
import com.example.moviescompose.data.model.toEntityList
import com.example.moviescompose.domain.model.MovieEntity
import com.example.moviescompose.utils.NetworkResult
import com.example.moviescompose.utils.safeApiCall
import javax.inject.Inject

sealed class MoviesState {
    object START : MoviesState()
    data class SUCCESS(val movieEntities: List<MovieEntity>) : MoviesState()
    object ERROR : MoviesState()
}

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource
) : MovieRepository {

    override suspend fun getMovies(): MoviesState {

        val response = safeApiCall {
            dataSource.getMovies()
        }


        return when (response) {
            is NetworkResult.Failure -> {
                MoviesState.ERROR
            }

            is NetworkResult.Success<MovieResponse> -> {
                MoviesState.SUCCESS(response.value?.feed?.entry?.map { it.toEntityList() }
                    ?: emptyList())
            }
        }
    }
}