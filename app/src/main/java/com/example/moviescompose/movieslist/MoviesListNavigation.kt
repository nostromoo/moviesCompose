/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.moviescompose.movieslist

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.moviescompose.domain.model.Movie

const val moviesListNavigationRoute = "moviesList"

fun NavController.navigateToMoviesList(navOptions: NavOptions? = null) {
    this.navigate(moviesListNavigationRoute, navOptions)
}

fun NavGraphBuilder.moviesListScreen(onMovieClick: (Movie) -> Unit) {
    composable(route = moviesListNavigationRoute,
        arguments = listOf(navArgument("sefsf") { type = NavType.LongType })) {
        MoviesScreen(onMovieClick = onMovieClick)
    }
}
