/*
 * Author:    Ali Al Rubaye
 * Date:      12/18/2025
 *
 * File Contents
 * This class contains implementation for GameModels a way to serialize and set the data from the RAWG API.
 */

package com.example.ludosira.gamelibrary.data
import com.google.gson.annotations.SerializedName


data class GameResponse(
    @SerializedName("results") val games: List<GameData>
)

data class Genre(
    val name: String
)

data class Developer(
    val name: String
)

data class Publisher(
    val name: String
)

data class GameData(
    @SerializedName("id") val id: Int,
    val name: String,
    @SerializedName("released") val releaseDate: String?,
    @SerializedName("background_image") val imageBackground: String?,
    @SerializedName("rating") val rating: Double,
    @SerializedName("description") val description: String?,
    @SerializedName("genres") val genres: List<Genre>?,
    @SerializedName("developers") val developers: List<Developer>?,
    @SerializedName("publishers") val publishers: List<Publisher>?,
)
