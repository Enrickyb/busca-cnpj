package br.com.buscacnpj.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.buscacnpj.data.api.ApiService
import br.com.buscacnpj.data.api.RetrofitClient
import br.com.buscacnpj.data.model.ApiResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiViewModel : ViewModel() {

    private val _apiData = MutableLiveData<ApiResponse>()
    val apiData: LiveData<ApiResponse> get() = _apiData

    private val apiService = RetrofitClient.instance.create(ApiService::class.java)

    fun fetchData(cnpj: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.fetchBusinessData(cnpj)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        _apiData.postValue(response.body())
                    } else {
                        Log.e("ApiViewModel", "Error: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("ApiViewModel", "Failure: ${t.message}")
                }
            })
        }
    }
}