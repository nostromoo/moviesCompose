package com.example.moviescompose.movieslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviescompose.R
import com.example.moviescompose.domain.model.Movie


@Composable
fun MoviesScreen(onMovieClick: (Movie) -> Unit) {

    val viewModel: MovieListViewModel = hiltViewModel()
    val moviesState by viewModel.movieState.collectAsStateWithLifecycle()

    LazyColumn {
        itemsIndexed(moviesState) { _, movie ->
            MovieCard(movie, onMovieClick)
        }
    }
}

@Composable
fun MovieCard(movie: Movie, onMovieClick: (Movie) -> Unit) {
    Column(Modifier.clickable { onMovieClick(movie) }) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.cover)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.popcorn),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1.5f, true)
                .height(300.dp)
                .fillMaxWidth(),
        )
        Text(
            text = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            style = MaterialTheme.typography.h1
        )
    }
}