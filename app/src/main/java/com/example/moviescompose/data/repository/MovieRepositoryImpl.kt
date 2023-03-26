package com.example.moviescompose.data.repository

import com.example.moviescompose.data.model.toEntityList
import com.example.moviescompose.di.Dispatcher
import com.example.moviescompose.di.MoviesDispatchers
import com.example.moviescompose.domain.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    @Dispatcher(MoviesDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: NetworkDataSource
) : MovieRepository, BaseRepository() {

    override fun getMovies(): Flow<List<Movie>> = flow {
        emit(
            dataSource.getMovies().body()?.map { it.toEntityList() } ?: emptyList()
        )
    }.flowOn(ioDispatcher)
}