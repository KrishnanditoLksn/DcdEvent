package app.ditodev.decedeevent.data.local.repository

import androidx.lifecycle.LiveData
import app.ditodev.decedeevent.data.local.dao.EventDao
import app.ditodev.decedeevent.data.local.entity.EventEntity
import app.ditodev.decedeevent.data.remote.api.service.ApiService

class FavoriteEventRepository(
    private val apiService: ApiService,
    private val eventDao: EventDao
) {
    fun getListEvent(): LiveData<List<EventEntity>> {
        return eventDao.getListEvents()
    }

    companion object {
        @Volatile
        private var INSTANCE: FavoriteEventRepository? = null

        fun getInstance(apiService: ApiService, eventDao: EventDao): FavoriteEventRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FavoriteEventRepository(apiService, eventDao).also { INSTANCE = it }
            }
        }
    }
}