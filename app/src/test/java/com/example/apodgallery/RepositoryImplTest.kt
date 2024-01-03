package com.example.apodgallery

import com.example.apodgallery.model.db.FavoriteDao
import com.example.apodgallery.model.network.ApodApiService
import com.example.apodgallery.model.network.ApodResponse
import com.example.apodgallery.repository.RepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class RepositoryImplTest {

    private lateinit var repository: RepositoryImpl
    private val apiService: ApodApiService = mock(ApodApiService::class.java)
    private val favoriteDao: FavoriteDao = mock(FavoriteDao::class.java)

    @Before
    fun setup() {
        repository = RepositoryImpl(apiService, favoriteDao)
    }

    @Test
    fun `get apods from api returns list`() = runBlocking {
        val fakeApods = listOf(
            ApodResponse("Test Title", "2023-01-01", "Explanation test","https://example.com/image.jpg")
        )
        `when`(apiService.loadApods(anyString(), anyInt()))
            .thenReturn(fakeApods)

        val result = repository.getApodsFromApi(10)

        verify(apiService).loadApods(anyString(), eq(10))
        assertEquals(fakeApods, result)

    }
}