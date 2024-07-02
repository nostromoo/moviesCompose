package com.example.moviescompose.details

import androidx.lifecycle.ViewModel
import com.example.moviescompose.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _isPreviewVisible = MutableStateFlow(true)
    val isPreviewVisible: StateFlow<Boolean> = _isPreviewVisible.asStateFlow()

    fun update(value: Boolean) {
        _isPreviewVisible.value = value
    }
}