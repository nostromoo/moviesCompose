package com.example.moviescompose.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Movie(
    val title: String,
    val cover: String?,
    val videoUrl: String?,
    val videoHeight: Int,
    val videoWidth: Int,
    val officialUrl: String?,
    val synopsis: String?,
    val releaseDate: String?,
    val rating: String?,
    val directors: List<String>,
    val writers: List<String>,
    val actors: List<String>
): Parcelable {
    fun releaseLabel() =
        " Release date : $releaseDate"

    fun ratingLabel() =
        " Rating :\n ${rating?.uppercase()}"

    fun directorsLabel() =
        " Director :\n ${directors.joinToString(", ")}"

    fun writersLabel() =
        " Writer :\n ${writers.joinToString(", ")}"

    fun actorsLabel() =
        " Actors :\n ${actors.joinToString(", ")}"

}