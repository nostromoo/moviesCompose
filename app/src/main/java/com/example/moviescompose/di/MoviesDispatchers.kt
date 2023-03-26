package com.example.moviescompose.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val movieDispatcher: MoviesDispatchers)

enum class MoviesDispatchers {
    DEFAULT,
    IO,
    MAIN
}
