package com.example.apodgallery.apod.presenter

import android.content.Context
import com.example.apodgallery.model.network.ApodResponse

interface ApodContract {

    interface Presenter {
        fun attachView(view: View)
        fun loadApodList(count: Int)
        fun addFavorite(apodResponse: ApodResponse)
        fun detachView()
    }

    interface View {
        fun showApods(apods: List<ApodResponse>)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showLoading(isLoading: Boolean)
    }
}