package com.alexrojasb.idpokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sprite (val back_default: String, val front_default: String) : Parcelable