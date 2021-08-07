package com.alexrojasb.idpokemon.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexrojasb.idpokemon.models.Move
import com.alexrojasb.idpokemon.models.Sprite
import com.alexrojasb.idpokemon.models.Stat
import com.alexrojasb.idpokemon.models.Types

@Entity
data class PokemonEntity(
    @PrimaryKey val name: String,
    var id: Int? = null,
    var weight: Int? = null,
    var height: Int? = null,
) {
}