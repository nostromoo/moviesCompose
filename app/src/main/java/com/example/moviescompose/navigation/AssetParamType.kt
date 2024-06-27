package com.example.moviescompose.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.moviescompose.domain.model.MovieEntity
import com.google.gson.Gson

class AssetParamType : NavType<MovieEntity>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): MovieEntity? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): MovieEntity {
        return Gson().fromJson(value, MovieEntity::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: MovieEntity) {
        bundle.putParcelable(key, value)
    }
}