package com.alexrojasb.idpokemon.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alexrojasb.idpokemon.database.PokemonEntity

@Dao
interface PokemonDao {
    @Query("Select * from pokemonentity where name=:name")
    fun getAllPokemonsByName(name: String): LiveData<List<PokemonEntity>>

    @Insert
    suspend fun insertPokemon(pokemonEntity: PokemonEntity)

    @Delete
    suspend fun deletePokemon(pokemonEntity: PokemonEntity)

}