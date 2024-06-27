package com.example.moviescompose.navigation

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviescompose.details.DetailsScreen
import com.example.moviescompose.domain.model.MovieEntity
import com.example.moviescompose.movieslist.MoviesScreen
import com.google.gson.Gson

const val detailsNavigationRoute = "details"
const val moviesListNavigationRoute = "moviesList"

@Composable
fun MoviesNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = moviesListNavigationRoute
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = moviesListNavigationRoute,
        ) {
            MoviesScreen(onMovieClick = { movie ->
                navController.navigateToDetails(movie)
            })
        }
        composable(
            route = "$detailsNavigationRoute/{movie}",
            arguments = listOf(
                navArgument("movie") {
                    type = AssetParamType()
                }
            )
        ) {
            BackHandler(true) {
            }
            val movie = it.arguments?.getParcelable<MovieEntity>("movie")

            DetailsScreen(movie)
        }
    }
}

fun NavController.navigateToDetails(
    movie: MovieEntity?,
) {
    val json = Uri.encode(Gson().toJson(movie))
    navigate("$detailsNavigationRoute/$json")
}

fun NavController.navigateToMoviesList() {
    navigate("$moviesListNavigationRoute")
}