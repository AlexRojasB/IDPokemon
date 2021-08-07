package com.alexrojasb.idpokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    var id: Int? = null,
    var weight: Int? = null,
    var height: Int? = null,
    var moves: ArrayList<Move>? = null,
    var sprites: Sprite? = null,
    var stats: ArrayList<Stat>? = null,
    var types: ArrayList<Types>? = null
    ) : Parcelable {
}