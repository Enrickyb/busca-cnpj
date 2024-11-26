package br.com.buscacnpj.data.repository

import br.com.buscacnpj.data.api.ApiService
import br.com.buscacnpj.data.api.RetrofitClient
import br.com.buscacnpj.data.model.ApiResponse
import retrofit2.Call
import retrofit2.create

class ApiRepository {

    private val apiService = RetrofitClient.instance.create(ApiService::class.java)

    suspend fun fetchData(cnpj: String): Call<ApiResponse> {
        return apiService.fetchBusinessData(cnpj)
    }
}