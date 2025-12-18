/*
 * Author:    Ali Al Rubaye
 * Date:      12/18/2025
 *
 * File Contents
 * This class contains implementation for RetrofitClient a way to gather the RAWG API to gather the data.
 */

package com.example.ludosira.core.network

import com.example.ludosira.gamelibrary.data.RawgApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
*This creates the "RawgApiService" implementation automatically
 */
object RetrofitClient {

    /**
     * The base URL for the RAWG API.
     */
    private const val BASE_URL = "https://api.rawg.io/api/"


    val service: RawgApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RawgApiService::class.java)
    }
}