package app.ditodev.decedeevent.ui.upcoming_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.ditodev.decedeevent.data.api.config.ApiConfig
import app.ditodev.decedeevent.data.api.response.EventResponse
import app.ditodev.decedeevent.data.api.response.ListEventsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingEventViewModel : ViewModel() {
    private var _listEvent = MutableLiveData<List<ListEventsItem>>()
    val listEvent: LiveData<List<ListEventsItem>> = _listEvent

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        fetchEvents()
    }
    private fun fetchEvents() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListEvents("1")
        client.enqueue(
            object : Callback<EventResponse> {
                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    _isLoading.value = false
                    val responseBody = response.body()

                    if (response.isSuccessful) {
                        /*
                        hello from API
                         */
                        _listEvent.value = responseBody?.listEvents
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    _isLoading.value = false
//                    Log.d("MainActivity")
                }
            }
        )
    }

}