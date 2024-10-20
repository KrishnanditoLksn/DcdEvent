package app.ditodev.decedeevent.di

import android.content.Context
import app.ditodev.decedeevent.data.local.EventDatabase
import app.ditodev.decedeevent.data.local.repository.DetailEventRepository
import app.ditodev.decedeevent.data.local.repository.FavoriteEventRepository
import app.ditodev.decedeevent.data.remote.api.config.ApiConfig

object DependencyInjection {
    fun provideRepository(context: Context): DetailEventRepository {
        val apiService = ApiConfig.getApiService()
        val database = EventDatabase.getInstance(context)
        val dao = database.eventDao()
        return DetailEventRepository.getInstance(apiService, dao)
    }

    fun provideFavoriteRepository(context: Context): FavoriteEventRepository {
        val apiService = ApiConfig.getApiService()
        val database = EventDatabase.getInstance(context)
        val dao = database.eventDao()
        return FavoriteEventRepository.getInstance(apiService, dao)
    }
}