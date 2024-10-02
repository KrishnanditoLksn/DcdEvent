package app.ditodev.decedeevent.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.ditodev.decedeevent.data.api.config.ApiConfig
import app.ditodev.decedeevent.data.api.response.EventResponse
import app.ditodev.decedeevent.data.api.response.ListEventsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEventViewModel : ViewModel() {
    private var _listEvent = MutableLiveData<ListEventsItem?>()
    val listEvent:LiveData<ListEventsItem?> = _listEvent

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    fun fetchDetailEvents(eventId : Int) {
//        _isLoading.value = true

        val client = ApiConfig.getApiService().getEventDetail(eventId)

        client.enqueue(
            (object : Callback<EventResponse> {
                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    val responseBody = response.body()
                    Log.d("IniRespondariBody", "onCreate: ${responseBody?.event?.toString()}")
                    if (response.isSuccessful) {
                        _listEvent.value = responseBody?.event
//                        Log.d("IniRespondariViewModel", "onCreate: ${responseBody?.event?.name.toString()}")
                    }
                }
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
//                    _isLoading.value = false
                }

            })
        )
    }
}