package com.example.apodgallery

import com.example.apodgallery.model.db.FavoriteApod
import com.example.apodgallery.model.network.ApodResponse
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FavoriteApodTest {

    @Test
    fun `convert ApodResponse to FavoriteApod correctly`() {
        val apodResponse = ApodResponse(
            "Title",
            "2023-01-01",
            "Explanation test",
            "https://example.com/image.jpg")

        val expectedResponse = FavoriteApod(
            title = "Title",
            date = "2023-01-01",
            explanation = "Explanation test",
            url = "https://example.com/image.jpg")

        val result = FavoriteApod.fromApodResponseToFavoriteApod(apodResponse)

        assertEquals(expectedResponse, result)
    }
}