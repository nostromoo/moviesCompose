package com.example.moviescompose.utils

fun durationStringToIsoDurationFormat(duration: String): String {
    return duration.replaceFirst("^(\\d{2}):(\\d{2}):(\\d{2})$", "PT$1H$2M$3S")
        .replaceFirst("^(\\d{2}):(\\d{2})$", "PT$1M$2S")
        .replaceFirst("^(\\d{2})$", "PT$1S")
}