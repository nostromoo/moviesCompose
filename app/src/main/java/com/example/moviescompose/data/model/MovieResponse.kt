package com.example.moviescompose.data.model

import com.example.moviescompose.domain.model.MovieEntity
import com.google.gson.annotations.SerializedName


data class MovieResponse(val feed: Feed)

data class Feed(val entry: List<Movie>)

data class Label(
    val label: String,
)

data class Image(
    val label: String,
    val attributes: ImageAttributes,
)

data class ImageAttributes(
    val height: String,
)

data class Price(
    val label: String,
    val attributes: PriceAttributes,
)

data class PriceAttributes(
    val amount: String,
    val currency: String,
)

data class ContentType(
    val label: String,
    val attributes: ContentTypeAttributes,
)

data class ContentTypeAttributes(
    val term: String,
    val label: String,
)

data class Link(
    val attributes: LinkAttributes,
)

data class LinkAttributes(
    val href: String,
    @SerializedName("im:assetType")
    val assetType: String,
)

data class ReleaseDate(
    val attributes: Label,
)

data class Movie(
    @SerializedName("im:name")
    val name: Label,
    val rights: Label,
    @SerializedName("im:image")
    val image: List<Image>,
    val summary: Label,
    @SerializedName("im:rentalPrice")
    val rentalPrice: Price,
    @SerializedName("im:price")
    val price: Price,
    @SerializedName("im:contentType")
    val contentType: ContentType,
    val link: List<Link>,
    @SerializedName("im:artist")
    val artist: Label,
    @SerializedName("im:releaseDate")
    val releaseDate: ReleaseDate,
) {
}

fun Movie.toEntityList(): MovieEntity {
    return MovieEntity(
        name.label,
        image.firstOrNull()?.label,
        link.firstOrNull { it.attributes.assetType == "preview" }?.attributes?.href,
        summary.label,
        releaseDate.attributes.label,
        rights.label,
        artist.label
    )
}