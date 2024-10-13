package app.ditodev.decedeevent.ui.favorite

import androidx.lifecycle.ViewModel
import app.ditodev.decedeevent.data.local.repository.FavoriteEventRepository

class FavoriteEventViewModel(private val favoriteEventRepository: FavoriteEventRepository) : ViewModel() {
    fun getListEvent() = favoriteEventRepository.getListEvent()
}