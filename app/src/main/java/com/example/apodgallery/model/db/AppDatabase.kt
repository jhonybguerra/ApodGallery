package com.example.apodgallery.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteApod::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}