package com.example.moviescompose.data.repository

interface MovieRepository {
    suspend fun getMovies(): MoviesState
}