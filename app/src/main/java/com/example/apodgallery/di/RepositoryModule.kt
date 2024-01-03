package com.example.apodgallery.di

import com.example.apodgallery.model.db.FavoriteDao
import com.example.apodgallery.model.network.ApodApiService
import com.example.apodgallery.repository.IRepository
import com.example.apodgallery.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApodApiService,
        favoriteDao: FavoriteDao
    ): IRepository {
        return RepositoryImpl(apiService, favoriteDao)
    }
}