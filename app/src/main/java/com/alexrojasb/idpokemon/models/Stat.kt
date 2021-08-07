package com.alexrojasb.idpokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stat(val baseStat: Int, val effort: Int, val stat: NameUrl) : Parcelable