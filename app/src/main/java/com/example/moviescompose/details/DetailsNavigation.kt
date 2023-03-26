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

package com.example.moviescompose.details

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.moviescompose.domain.model.Movie

const val detailsNavigationRoute = "details"

fun NavController.navigateToDetails(movie: Movie) {
    currentBackStackEntry?.arguments?.putParcelable("movie", movie)
    Log.d("rominou", "navigate currentBackStackEntry : ${currentBackStackEntry?.arguments}")
    navigate(detailsNavigationRoute)
}

fun NavGraphBuilder.detailsScreen(navController: NavController) {
    composable(
        route = detailsNavigationRoute,
    ) {
        val movie =
            navController.previousBackStackEntry?.arguments?.getParcelable("movie") as? Movie
        Log.d("rominou", "previous : ${navController.previousBackStackEntry?.arguments}")
        Log.d("rominou", "current : ${navController.currentBackStackEntry?.arguments}")
        requireNotNull(movie)
        DetailsScreen(movie)
    }
}
