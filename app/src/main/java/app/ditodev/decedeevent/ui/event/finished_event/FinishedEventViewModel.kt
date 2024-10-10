package app.ditodev.decedeevent.ui.event.finished_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.ditodev.decedeevent.data.remote.api.config.ApiConfig
import app.ditodev.decedeevent.data.remote.api.response.EventResponse
import app.ditodev.decedeevent.data.remote.api.response.ListEventsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishedEventViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    private var _finishedListEvents = MutableLiveData<List<ListEventsItem>>()
    val finishedListEvents: LiveData<List<ListEventsItem>> = _finishedListEvents

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        getFinishedEventsList()
    }

    private fun getFinishedEventsList() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListEvents("0")

        client.enqueue(
            object : Callback<EventResponse> {
                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        _finishedListEvents.value = responseBody?.listEvents
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    _isLoading.value = false
                }

            }
        )
    }
}