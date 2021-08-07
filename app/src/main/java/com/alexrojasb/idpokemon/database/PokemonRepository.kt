package com.alexrojasb.idpokemon.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.alexrojasb.idpokemon.database.dao.PokemonDao
import com.alexrojasb.idpokemon.models.Pokemon

class PokemonRepository(private val pokemonDao: PokemonDao) {
    fun allPokemons(): LiveData<List<PokemonEntity>> {
        return pokemonDao.getAllPokemons()
    }

    @WorkerThread
    suspend fun insert(pokemon: Pokemon) {
        pokemonDao.insertPokemon(convertToEntity(pokemon))
    }

    @WorkerThread
    suspend fun delete(pokemonEntity: PokemonEntity) {
        pokemonDao.deletePokemon(pokemonEntity)
    }

    private fun convertToEntity(pokemon: Pokemon) : PokemonEntity {
         var entity = PokemonEntity(
             name = pokemon.name,
             id = pokemon.id,
             weight = pokemon.weight,
             height = pokemon.height,
         )
        return entity
    }

    private fun convertToModel(pokemon: PokemonEntity) : Pokemon {
        var pokemon = Pokemon(
            name = pokemon.name,
            id = pokemon.id,
            weight = pokemon.weight,
            height = pokemon.height,
        )
        return pokemon
    }

}