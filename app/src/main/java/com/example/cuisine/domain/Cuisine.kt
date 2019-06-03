package com.example.cuisine.domain

import android.os.Parcel
import android.os.Parcelable

data class Cuisine(
    val title: String?,
    val description: String?,
    val photo: String?,
    val calorie: Double?,
    val chef: String?,
    val tags: List<String>?
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(photo)
        parcel.writeValue(calorie)
        parcel.writeString(chef)
        parcel.writeStringList(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cuisine> {
        override fun createFromParcel(parcel: Parcel): Cuisine {
            return Cuisine(parcel)
        }

        override fun newArray(size: Int): Array<Cuisine?> {
            return arrayOfNulls(size)
        }
    }
    fun getStringFromTagsList() = this.tags.toString().removeSurrounding("[", "]")
}