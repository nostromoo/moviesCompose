package com.example.moviescompose.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviescompose.R
import com.example.moviescompose.domain.model.Movie

@Composable
fun DetailsScreen(movie: Movie) {

    Column {
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
        if (movie.synopsis != null) {
            Text(text = movie.synopsis)
        }
        if (movie.directors.isNotEmpty()) {
            Text(text = movie.directorsLabel())
        }
        if (movie.writers.isNotEmpty()) {
            Text(text = movie.writersLabel())
        }
        if (movie.actors.isNotEmpty()) {
            Text(text = movie.actorsLabel())
        }
    }
}
