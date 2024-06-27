package com.example.moviescompose.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescompose.data.repository.MovieRepository
import com.example.moviescompose.data.repository.MoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    init {
        fetchMovies()
    }

    var movieState: MutableStateFlow<MoviesState> = MutableStateFlow(MoviesState.START)

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieState.value = movieRepository.getMovies()
        }
    }
}