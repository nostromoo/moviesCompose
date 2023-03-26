package com.example.moviescompose.data.repository

import com.example.moviescompose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
}