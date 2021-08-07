package com.alexrojasb.idpokemon.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alexrojasb.idpokemon.database.PokemonContext
import com.alexrojasb.idpokemon.database.PokemonEntity
import com.alexrojasb.idpokemon.database.PokemonRepository
import com.alexrojasb.idpokemon.models.Pokemon
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var pokemonRepository: PokemonRepository

    init {
        val db = PokemonContext.getDatabase(application)
        pokemonRepository = PokemonRepository(db.pokemonDao())
    }

    fun getAllPokemons(name: String): LiveData<List<PokemonEntity>> {
        return pokemonRepository.allPokemons()
    }

    fun delete(pokemon: Pokemon) = viewModelScope.launch {
        pokemonRepository.delete(PokemonEntity(pokemon.name))
    }
}