package com.example.moviescompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.moviescompose.details.detailsScreen
import com.example.moviescompose.details.navigateToDetails
import com.example.moviescompose.movieslist.moviesListScreen

@Composable
fun MoviesNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "moviesList"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        moviesListScreen(onMovieClick = { movie ->
            navController.navigateToDetails(movie)
        })
        detailsScreen(navController)
    }
}