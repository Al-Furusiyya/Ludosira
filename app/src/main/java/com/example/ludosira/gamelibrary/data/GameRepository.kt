package com.example.ludosira.gamelibrary.data

import com.example.ludosira.core.network.RetrofitClient

class GameRepository {
    private val retrofitClient = RetrofitClient.service

    suspend fun getGames(apiKey: String): GameResponse {
        return retrofitClient.getGames(apiKey)
    }
}
