package com.alexrojasb.idpokemon.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}