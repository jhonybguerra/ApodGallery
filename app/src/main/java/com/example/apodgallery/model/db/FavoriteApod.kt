package com.example.apodgallery.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apodgallery.model.network.ApodResponse

@Entity(tableName = "favorites_apod")
data class FavoriteApod(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val title: String,
    val date: String,
    val explanation: String,
    val url: String
) {
    companion object {
        fun fromApodResponseToFavoriteApod(
            apodResponse: ApodResponse
        ) : FavoriteApod {
            return FavoriteApod(
                title = apodResponse.title,
                date = apodResponse.date,
                explanation = apodResponse.explanation,
                url = apodResponse.url
            )
        }
    }
}