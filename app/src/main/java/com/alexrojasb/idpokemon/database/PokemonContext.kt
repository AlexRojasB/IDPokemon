package com.alexrojasb.idpokemon.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexrojasb.idpokemon.database.dao.PokemonDao

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonContext : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var Instance: PokemonContext? = null

        fun getDatabase(context: Context): PokemonContext {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonContext::class.java,
                    "pokemon_db"
                ).build()
                Instance = instance
                instance
            }

        }
    }
}