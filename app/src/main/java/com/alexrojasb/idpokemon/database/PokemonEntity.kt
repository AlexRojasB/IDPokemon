package com.alexrojasb.idpokemon.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(@PrimaryKey val name: String) {
}