package com.alexrojasb.idpokemon.models

import android.os.Parcel
import android.os.Parcelable


data class Pokemon(val name: String, val url: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!, parcel.readString()!!
    ) {
    }



    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let{
            dest.writeString(name)
            dest.writeString(url)
        }
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}