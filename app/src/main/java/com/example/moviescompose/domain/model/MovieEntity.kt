package com.example.moviescompose.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val title: String,
    val cover: String,
    val coverHeight: Int,
    val videoUrl: String?,
    val synopsis: String?,
    val releaseDate: String?,
    val rights: String?,
    val artist: String?,
): Parcelable {
    fun releaseLabel() =
        "Release date : $releaseDate"
}