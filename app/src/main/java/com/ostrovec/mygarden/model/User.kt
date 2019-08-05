package com.ostrovec.mygarden.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int,

        var email: String?): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(email)
        }

        override fun describeContents(): Int = 0

        companion object CREATOR : Parcelable.Creator<User> {
                override fun createFromParcel(parcel: Parcel): User = User(parcel)

                override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
}