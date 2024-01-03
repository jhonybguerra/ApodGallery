package com.example.apodgallery.model.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiService {

    @GET("/planetary/apod")
    suspend fun loadApods(
        @Query("api_key") apiKey: String,
        @Query("count") count: Int
    ) : List<ApodResponse>
}