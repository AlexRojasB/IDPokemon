package com.alexrojasb.idpokemon.services

import com.alexrojasb.idpokemon.models.PokeApiResult
import com.alexrojasb.idpokemon.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon/")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int) : Call<PokeApiResult>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") idOrName: String) : Call<Pokemon>
}