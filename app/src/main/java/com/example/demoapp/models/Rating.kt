package com.example.demoapp.models

import android.os.Parcel
import android.os.Parcelable

data class Rating (
    val count: Int,
    val rate: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(count)
        dest.writeDouble(rate)
    }

    companion object CREATOR : Parcelable.Creator<Rating> {
        override fun createFromParcel(parcel: Parcel): Rating {
            return Rating(parcel)
        }

        override fun newArray(size: Int): Array<Rating?> {
            return arrayOfNulls(size)
        }
    }
}