package app.ditodev.decedeevent.data.local.repository

import androidx.lifecycle.LiveData
import app.ditodev.decedeevent.data.local.dao.EventDao
import app.ditodev.decedeevent.data.local.entity.EventEntity
import app.ditodev.decedeevent.data.remote.api.service.ApiService

class DetailEventRepository(
    private val apiService: ApiService,
    private val eventDao: EventDao
) {

    fun getFavoriteEventById(id: String): LiveData<EventEntity> {
        return eventDao.getFavoriteEventById(id)
    }

    suspend fun insertEvent(event: EventEntity) {
        eventDao.insertEvent(event)
    }

    suspend fun deleteEvent(eventId: String) {
        eventDao.deleteAll(eventId)
    }


    companion object {
        @Volatile
        private var INSTANCE: DetailEventRepository? = null

        fun getInstance(apiService: ApiService, eventDao: EventDao): DetailEventRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DetailEventRepository(apiService, eventDao).also { INSTANCE = it }
            }
        }
    }
}