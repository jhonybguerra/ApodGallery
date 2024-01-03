package com.example.apodgallery.repository

import com.example.apodgallery.di.NetworkModule.API_KEY
import com.example.apodgallery.model.db.FavoriteApod
import com.example.apodgallery.model.db.FavoriteDao
import com.example.apodgallery.model.network.ApodApiService
import com.example.apodgallery.model.network.ApodResponse
import javax.inject.Inject
import javax.inject.Named

class RepositoryImpl @Inject constructor(
    private val apiService: ApodApiService,
    private val favoriteDao: FavoriteDao
) : IRepository {

    override suspend fun getApodsFromApi(count: Int): List<ApodResponse> {
        return apiService.loadApods(API_KEY, count)
    }

    override suspend fun getAllFavorites(): List<FavoriteApod> {
        return favoriteDao.getAllFavorites()
    }

    override suspend fun addFavorite(favoriteApod: FavoriteApod) {
        return favoriteDao.addFavorite(favoriteApod)
    }

    override suspend fun removeFavorite(favoriteId: Int) {
        return favoriteDao.removefavorite(favoriteId)
    }
}