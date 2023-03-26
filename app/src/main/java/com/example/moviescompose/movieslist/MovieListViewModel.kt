package com.example.moviescompose.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescompose.data.repository.MovieRepository
import com.example.moviescompose.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(movieRepository: MovieRepository) :
    ViewModel() {

    var movieState: StateFlow<List<Movie>> = movieRepository.getMovies().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )
}