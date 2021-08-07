package com.alexrojasb.idpokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sprite (val backDefault: String, val frontDefault: String) : Parcelable