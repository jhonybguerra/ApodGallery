package com.example.apodgallery.model.network

import com.google.gson.annotations.SerializedName

data class ApodResponse(
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: String,
    @SerializedName("explanation") val explanation: String,
    @SerializedName("url") val url: String
    )