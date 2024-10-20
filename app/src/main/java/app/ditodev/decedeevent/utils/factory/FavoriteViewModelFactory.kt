package app.ditodev.decedeevent.utils.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.ditodev.decedeevent.data.local.repository.FavoriteEventRepository
import app.ditodev.decedeevent.di.DependencyInjection
import app.ditodev.decedeevent.ui.favorite.FavoriteEventViewModel

class FavoriteViewModelFactory private constructor(private val favoriteEventRepository: FavoriteEventRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteEventViewModel::class.java)) {
            return FavoriteEventViewModel(favoriteEventRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: FavoriteViewModelFactory? = null
        fun getInstance(context: Context): FavoriteViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: FavoriteViewModelFactory(DependencyInjection.provideFavoriteRepository(context))
            }.also { instance = it }
    }
}