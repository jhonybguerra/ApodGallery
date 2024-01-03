package com.example.apodgallery

import com.example.apodgallery.favorites.presenter.FavoritesContract
import com.example.apodgallery.favorites.presenter.FavoritesPresenterImpl
import com.example.apodgallery.repository.IRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class FavoritesPresenterImplTest {

    private lateinit var presenter: FavoritesPresenterImpl
    private val repository: IRepository = mock(IRepository::class.java)
    private val view: FavoritesContract.View = mock(FavoritesContract.View::class.java)

    @Before
    fun setup() {
        presenter = FavoritesPresenterImpl(repository)
    }

    @Test
    fun `attach view successfully`() {
        presenter.attachView(view)
    }
}