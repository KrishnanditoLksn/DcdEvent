package app.ditodev.decedeevent.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.ditodev.decedeevent.data.api.config.ApiConfig
import app.ditodev.decedeevent.data.api.response.DetailEventResponse
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEventViewModel : ViewModel() {
    private var _detailEvent = MutableLiveData<DetailEventResponse?>()
    val detailEvent: LiveData<DetailEventResponse?> = _detailEvent

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun fetchDetailEvents(eventId: Int) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getEventDetail(eventId)

        client.enqueue(
            (object : Callback<DetailEventResponse> {
                override fun onResponse(
                    call: Call<DetailEventResponse>,
                    response: Response<DetailEventResponse>
                ) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        _detailEvent.value = responseBody
                    }
                }

                override fun onFailure(call: Call<DetailEventResponse>, t: Throwable) {
                    _isLoading.value = false
                }

            })
        )
    }
}