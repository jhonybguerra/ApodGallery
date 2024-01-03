package com.example.apodgallery.apod.presenter

import com.example.apodgallery.model.db.FavoriteApod
import com.example.apodgallery.model.network.ApodResponse
import com.example.apodgallery.repository.IRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class ApodPresenterImpl @Inject constructor(
    private val repository: IRepository
) : ApodContract.Presenter {

    private var view: ApodContract.View? = null
    private var presenterScope: CoroutineScope? = null
    private var cachedApods: List<ApodResponse>? = null

    override fun attachView(view: ApodContract.View) {
        this.view = view
        this.presenterScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    }

    override fun loadApodList(count: Int) {
        cachedApods?.let {
            view?.showApods(it)
            return
        }
        presenterScope?.launch {
            try {
                val apods = withContext(Dispatchers.IO) {
                    repository.getApodsFromApi(count)
                }
                cachedApods = apods
                withContext(Dispatchers.Main) {
                    view?.showApods(apods)
                }
            } catch(e: Exception) {
                withContext(Dispatchers.Main) {
                    view?.showError(e.message ?: "Failed to load images")
                }
            }
        }
    }

    override fun addFavorite(apodResponse: ApodResponse) {
        presenterScope?.launch {
            try {
                withContext(Dispatchers.IO) {
                    val favorite = FavoriteApod.fromApodResponseToFavoriteApod(apodResponse)
                    repository.addFavorite(favorite)
                }
                withContext(Dispatchers.Main) {
                    view?.showSuccess("Image added to favorites")
                }
            } catch(e: Exception) {
                withContext(Dispatchers.Main) {
                    view?.showError(e.message ?: "Failed to add image to favorites")
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