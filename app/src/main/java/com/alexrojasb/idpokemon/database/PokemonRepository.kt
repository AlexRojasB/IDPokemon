package com.alexrojasb.idpokemon.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.alexrojasb.idpokemon.database.dao.PokemonDao

class PokemonRepository(private val pokemonDao: PokemonDao) {
    fun allPokemonsByName(name: String): LiveData<List<PokemonEntity>> {
        return pokemonDao.getAllPokemonsByName(name)
    }

    @WorkerThread
    suspend fun insert(pokemonEntity: PokemonEntity) {
        pokemonDao.insertPokemon(pokemonEntity)
    }

    @WorkerThread
    suspend fun delete(pokemonEntity: PokemonEntity) {
        pokemonDao.deletePokemon(pokemonEntity)
    }
}