package app.ditodev.decedeevent.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ditodev.decedeevent.data.local.entity.EventEntity
import app.ditodev.decedeevent.data.local.repository.DetailEventRepository
import app.ditodev.decedeevent.data.remote.api.config.ApiConfig
import app.ditodev.decedeevent.data.remote.api.response.DetailEventResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEventViewModel(private val repository: DetailEventRepository) : ViewModel() {
    private var _detailEvent = MutableLiveData<DetailEventResponse?>()
    val detailEvent: LiveData<DetailEventResponse?> = _detailEvent

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getFavoriteEventById(id: String) = repository.getFavoriteEventById(id)

    fun insert(event: EventEntity) {
        viewModelScope.launch {
            repository.insertEvent(event)
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            repository.deleteEvent(id)
        }
    }


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