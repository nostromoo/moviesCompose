package com.example.moviescompose.data.model

import com.example.moviescompose.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Page,
    val heros: Heros,
    val details: Details,
    val clips: List<Clip>
) {
    class Page(
        val movie_title: String,
        val movie_rating: String,
        val release_copy: String
    )

    class Heros(
        @SerializedName("0")
        val locale: Locale
    ) {
        class Locale(
            val imageurl: String
        )
    }

    class Details(
        val official_url: String,
        val locale: Locale
    ) {
        class Locale(
            val en: En
        ) {
            class En(
                val synopsis: String,
                val castcrew: CastCrew?
            ) {
                class CastCrew(
                    val directors: List<People>?,
                    val writers: List<People>?,
                    val actors: List<People>?
                ) {
                    class People(
                        val name: String
                    )
                }
            }
        }
    }


    class Clip(
        val versions: Versions
    ) {
        class Versions(
            val enus: Enus
        ) {
            class Enus(
                val sizes: Sizes
            ) {
                class Sizes(
                    val sd: Sd
                ) {
                    class Sd(
                        val srcAlt: String,
                        val height: String,
                        val width: Int
                    )
                }
            }
        }
    }
}

fun MovieResponse.toEntityList(): Movie {
    return Movie(page.movie_title,
        heros.locale.imageurl,
        clips[0].versions.enus.sizes.sd.srcAlt,
        clips[0].versions.enus.sizes.sd.height.toInt(),
        clips[0].versions.enus.sizes.sd.width,
        details.official_url,
        details.locale.en.synopsis,
        page.release_copy,
        page.movie_rating,
        if (details.locale.en.castcrew?.directors != null) details.locale.en.castcrew.directors.map { director -> director.name } else arrayListOf(),
        if (details.locale.en.castcrew?.writers != null) details.locale.en.castcrew.writers.map { writer -> writer.name } else arrayListOf(),
        if (details.locale.en.castcrew?.actors != null) details.locale.en.castcrew.actors.map { actor -> actor.name } else arrayListOf())
}