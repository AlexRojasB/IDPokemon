package com.alexrojasb.idpokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val id: Int? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val moves: ArrayList<Move>? = null,
    val sprites: ArrayList<Sprite>? = null,
    val stats: ArrayList<Stat>? = null,
    val types: ArrayList<Types>? = null
    ) : Parcelable {
}