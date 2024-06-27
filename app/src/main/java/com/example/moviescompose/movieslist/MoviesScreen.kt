package com.example.moviescompose.movieslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.moviescompose.data.repository.MoviesState
import com.example.moviescompose.domain.model.MovieEntity


@Composable
fun MoviesScreen(onMovieClick: (MovieEntity) -> Unit) {

    val viewModel: MovieListViewModel = hiltViewModel()
    val moviesState by viewModel.movieState.collectAsStateWithLifecycle()

    when (moviesState) {
        is MoviesState.SUCCESS -> {
            LazyColumn {
                itemsIndexed((moviesState as MoviesState.SUCCESS).movieEntities) { _, movie ->
                    MovieCard(movie, onMovieClick)
                }
            }
        }

        MoviesState.ERROR -> {}
        MoviesState.START -> {}
    }
}

@Composable
fun MovieCard(movieEntity: MovieEntity, onMovieClick: (MovieEntity) -> Unit) {
    Column(Modifier.clickable { onMovieClick(movieEntity) }) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieEntity.cover)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.popcorn),
            contentDescription = movieEntity.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1.5f, true)
                .height(300.dp)
                .fillMaxWidth(),
        )
        Text(
            text = movieEntity.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            style = MaterialTheme.typography.h1
        )
    }
}