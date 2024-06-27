package com.example.moviescompose.details

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviescompose.R
import com.example.moviescompose.domain.model.MovieEntity

@Composable
fun DetailsScreen(movieEntity: MovieEntity?) {

    val isPreviewVisible = remember { mutableStateOf(true) }

    Column {
        VideoView(isPreviewVisible, movieEntity)
        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            movieEntity?.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h1,
                )
            }
            movieEntity?.synopsis?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                )
            }

            movieEntity?.artist?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2,
                )

            }

            movieEntity?.rights?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2,
                )
            }
            movieEntity?.releaseDate?.let {
                Text(
                    text = movieEntity.releaseLabel(),
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
private fun VideoView(
    isPreviewVisible: MutableState<Boolean>,
    movieEntity: MovieEntity?
) {
    Surface {
        if (isPreviewVisible.value) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieEntity?.cover)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.popcorn),
                contentDescription = movieEntity?.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1.5f, true)
                    .height(300.dp)
                    .fillMaxWidth(),
            )
        }
        movieEntity?.videoUrl?.let {
            ExoPlayerView(it) { isVisible ->
                isPreviewVisible.value = isVisible
            }
        }
    }
}

@Composable
fun ExoPlayerView(videoUrl: String, onUpdate: (Boolean) -> Unit) {
    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    // Create a MediaSource
    val mediaSource = remember(videoUrl) {
        MediaItem.fromUri(videoUrl)
    }

    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            exoPlayer.pause()
        }
    }

    // Use AndroidView to embed an Android View (PlayerView) into Compose
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier
            .aspectRatio(1.5f, true)
            .height(300.dp)
            .fillMaxWidth(),
        update = {
            onUpdate(false)
        }
    )
}

@Composable
fun ComposableLifecycle(
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}