package com.example.apodgallery.repository

import com.example.apodgallery.model.db.FavoriteApod
import com.example.apodgallery.model.network.ApodResponse

interface IRepository {

    suspend fun getApodsFromApi(count: Int) : List<ApodResponse>
    suspend fun getAllFavorites() : List<FavoriteApod>
    suspend fun addFavorite(favoriteApod: FavoriteApod)
    suspend fun removeFavorite(favoriteId: Int)

}