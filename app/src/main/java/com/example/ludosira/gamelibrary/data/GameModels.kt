

package com.example.ludosira.gamelibrary.data
import com.google.gson.annotations.SerializedName


data class GameResponse(
    @SerializedName("results") val games: List<GameDto>
)


data class GameDto(
    val id: Int,
    val name: String,
    @SerializedName("released") val releaseDate: String?,
    @SerializedName("background_image") val imageBackground: String?,
    @SerializedName("rating") val rating: Double
)