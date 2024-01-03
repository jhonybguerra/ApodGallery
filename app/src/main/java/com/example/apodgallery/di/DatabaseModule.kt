package com.example.apodgallery.di

import android.content.Context
import androidx.room.Room
import com.example.apodgallery.model.db.AppDatabase
import com.example.apodgallery.model.db.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: AppDatabase) : FavoriteDao {
        return database.favoriteDao()
    }
}