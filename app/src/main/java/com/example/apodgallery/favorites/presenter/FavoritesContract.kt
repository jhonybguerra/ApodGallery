package com.example.apodgallery.favorites.presenter

import com.example.apodgallery.model.db.FavoriteApod

interface FavoritesContract {

    interface Presenter {
        fun attachView(view: View)
        fun loadFavoritesList()
        fun removeFavorites(favoriteId: Int)
        fun detachView()
    }

    interface View {
        fun showFavorites(apods: List<FavoriteApod>)
        fun showSuccess(message: String)
        fun showError(message: String)
    }
}