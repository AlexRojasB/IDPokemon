package com.alexrojasb.idpokemon.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexrojasb.idpokemon.models.PokeApiResult
import com.alexrojasb.idpokemon.models.Pokemon
import com.alexrojasb.idpokemon.services.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel: ViewModel() {
    private  val retrofitProvider = RetrofitProvider()
    private var newPokemonOffset = 0
    private val localPokemon = ArrayList<Pokemon>()

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _serverError = MutableLiveData<Boolean>()
    val serverError: LiveData<Boolean> = _serverError

    fun fetchPokemonList() {
        _isLoading.postValue(true)
        retrofitProvider.getApiService().getPokemonList(10, newPokemonOffset)
            .enqueue(object : Callback<PokeApiResult> {
                override fun onResponse(
                    call: Call<PokeApiResult>,
                    response: Response<PokeApiResult>
                ) {
                    if (response.isSuccessful) {
                        newPokemonOffset += 15
                        localPokemon.addAll(response.body()!!.results)
                        _isLoading.postValue(false)
                        _serverError.postValue(false)
                        _pokemonList.postValue(localPokemon)
                    } else {
                        _serverError.postValue(true)
                    }
                }

                override fun onFailure(call: Call<PokeApiResult>, t: Throwable) {
                    _isLoading.postValue(false)
                    _serverError.postValue(true)
                }

            })
    }
}