package br.com.buscacnpj.data.api

import br.com.buscacnpj.data.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{cnpj}")
    fun fetchBusinessData(@Path("cnpj") cnpj: String): Call<ApiResponse>
}