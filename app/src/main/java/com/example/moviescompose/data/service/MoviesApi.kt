package com.example.moviescompose.data.service

import com.example.moviescompose.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("/movies.json")
    suspend fun fetchMovies(): Response<MovieResponse>
}