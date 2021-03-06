package com.alexrojasb.idpokemon.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.alexrojasb.idpokemon.database.PokemonContext
import com.alexrojasb.idpokemon.database.PokemonEntity
import com.alexrojasb.idpokemon.database.PokemonRepository
import com.alexrojasb.idpokemon.models.PokeApiResult
import com.alexrojasb.idpokemon.models.Pokemon
import com.alexrojasb.idpokemon.services.RetrofitProvider
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel(application: Application): AndroidViewModel(application) {
    private  val retrofitProvider = RetrofitProvider()
    private var newPokemonOffset = 0
    private lateinit var pokemonRepository: PokemonRepository

    private val localPokemon = ArrayList<Pokemon>()
    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _serverError = MutableLiveData<Boolean>()
    val serverError: LiveData<Boolean> = _serverError

    private val _pokemonUpdateDetails = MutableLiveData<Pokemon>()
    val pokemonUpdateDetails: LiveData<Pokemon> = _pokemonUpdateDetails

    init {
        //var db = PokemonContext.getDatabase(application)
       // pokemonRepository = PokemonRepository(db.pokemonDao())

        //localPokemon.addAll(pokemonRepository.allPokemons())
    }

    fun insert(pokemon: Pokemon) = viewModelScope.launch {
        //pokemonRepository.insert(pokemon)
    }



    fun fetchOneRandomPokemon() {
        val rnds = (1..898).random()
        _isLoading.postValue(true)
        retrofitProvider.getApiService().getPokemon(rnds.toString())
            .enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        val poke = response.body()
                       var isInTheList = localPokemon.firstOrNull{ pokemon -> pokemon.name == poke!!.name}
                        if (isInTheList == null) {
                            localPokemon.add(poke!!)
                        } else if ( poke?.id != null) {
                            isInTheList.id = poke?.id
                            isInTheList.height = poke?.height
                            isInTheList.weight = poke?.weight
                            isInTheList.moves = poke?.moves
                            isInTheList.types = poke?.types
                            isInTheList.stats = poke?.stats
                            isInTheList.sprites = poke?.sprites
                        }
                        _isLoading.postValue(false)
                        _serverError.postValue(false)
                        _pokemonList.postValue(localPokemon)
                    } else {
                        _serverError.postValue(true)
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    _serverError.postValue(true)
                }
            })
    }

    fun fetchOnePokemon(name: String) {
        _isLoading.postValue(true)
        retrofitProvider.getApiService().getPokemon(name)
            .enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        val poke = response.body()!!

                        _isLoading.postValue(false)
                        _serverError.postValue(false)
                        _pokemonUpdateDetails.postValue(poke)
                    } else {
                        _serverError.postValue(true)
                    }
                }

                override fun onFailure(call: Call<Pokemon?>, t: Throwable) {
                    _serverError.postValue(true)
                }
            })
    }

    fun fetchPokemonList() {
        _isLoading.postValue(true)
        retrofitProvider.getApiService().getPokemonList(10, newPokemonOffset)
            .enqueue(object : Callback<PokeApiResult> {
                override fun onResponse(
                    call: Call<PokeApiResult>,
                    response: Response<PokeApiResult>
                ) {
                    if (response.isSuccessful) {
                        newPokemonOffset += 10
                        val pokemonMapped = response.body()!!.results.map { poke -> Pokemon(name = poke.name) }
                        localPokemon.addAll(pokemonMapped)
                        _isLoading.postValue(false)
                        _serverError.postValue(false)
                        _pokemonList.postValue(localPokemon)
                    } else {
                        _serverError.postValue(true)
                        _isLoading.postValue(false)
                    }
                }

                override fun onFailure(call: Call<PokeApiResult>, t: Throwable) {
                    _isLoading.postValue(false)
                    _serverError.postValue(true)
                }

            })
    }
}