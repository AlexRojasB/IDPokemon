package com.alexrojasb.idpokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Types(
    val slot: Int,
    val type: NameUrl
) : Parcelable