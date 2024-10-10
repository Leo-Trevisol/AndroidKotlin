package com.project.retrofit.network

import retrofit2.http.GET
import retrofit2.http.Path

interface AdressService {

    @GET("https://viacep.com.br/ws/{cep}/json/")
    fun findAdress(@Path("cep") cep : String)
}