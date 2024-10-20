package app.ditodev.decedeevent.utils.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.ditodev.decedeevent.data.local.repository.DetailEventRepository
import app.ditodev.decedeevent.di.DependencyInjection
import app.ditodev.decedeevent.ui.detail.DetailEventViewModel

class DetailEventViewModelFactory private constructor(private val eventRepository: DetailEventRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailEventViewModel::class.java)) {
            return DetailEventViewModel(eventRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: DetailEventViewModelFactory? = null
        fun getInstance(context: Context): DetailEventViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetailEventViewModelFactory(DependencyInjection.provideRepository(context))
            }.also { instance = it }
    }
}