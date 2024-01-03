package com.example.apodgallery.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites_apod")
    suspend fun getAllFavorites(): List<FavoriteApod>

    @Insert
    suspend fun addFavorite(favoriteApod: FavoriteApod)

    @Query("DELETE FROM favorites_apod WHERE id = :favoriteId")
    suspend fun removefavorite(favoriteId: Int)
}