package app.ditodev.decedeevent.data.api.service

import app.ditodev.decedeevent.data.api.response.EventResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/events")
    fun getListEvents(@Query("active") page: String): Call<EventResponse>
}