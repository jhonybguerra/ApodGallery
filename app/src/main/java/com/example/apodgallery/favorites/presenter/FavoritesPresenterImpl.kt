package com.example.apodgallery.favorites.presenter

import com.example.apodgallery.repository.IRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritesPresenterImpl @Inject constructor(
    private val repository: IRepository
) : FavoritesContract.Presenter {

    private var view: FavoritesContract.View? = null
    private var presenterScope: CoroutineScope? = null

    override fun attachView(view: FavoritesContract.View) {
        this.view = view
        this.presenterScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    }

    override fun loadFavoritesList() {
        presenterScope?.launch {
            try {
                val favorites = withContext(Dispatchers.IO) {
                        repository.getAllFavorites()
                }
                withContext(Dispatchers.Main) {
                    view?.showFavorites(favorites)
                }
            } catch(e: Exception) {
                withContext(Dispatchers.Main) {
                    view?.showError(e.message ?: "Failed to load favorites")
                }
            }
        }
    }

    override fun removeFavorites(favoriteId: Int) {
        presenterScope?.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.removeFavorite(favoriteId)
                }
                withContext(Dispatchers.Main) {
                    view?.showSuccess("Image removed from favorites")
                }
                loadFavoritesList()
            } catch(e: Exception) {
                withContext(Dispatchers.Main) {
                    view?.showError(e.message ?: "Error removing from favorites")
                }
            }
        }
    }

    override fun detachView() {
        presenterScope?.cancel()
        presenterScope = null
        view = null
    }
}