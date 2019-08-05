package com.ostrovec.mygarden.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Plant(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        var name: String?,

        @SerializedName("irrigation_period")
        var irrigationPeriod: Long,

        @SerializedName("url_local_photo")
        var urlLocalPhoto: String?,

        @SerializedName("url_server_photo")
        var urlServerPhoto: String?,

        @SerializedName("start_irrigation")
        var startIrrigation: Long,

        @SerializedName("")
        var endIrrigation: Long):Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readLong(),
                parcel.readString(),
                parcel.readString(),
                parcel.readLong(),
                parcel.readLong()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeLong(irrigationPeriod)
                parcel.writeString(urlLocalPhoto)
                parcel.writeString(urlServerPhoto)
                parcel.writeLong(startIrrigation)
                parcel.writeLong(endIrrigation)
        }

        override fun describeContents(): Int = 0

        companion object CREATOR : Parcelable.Creator<Plant> {
                override fun createFromParcel(parcel: Parcel): Plant = Plant(parcel)

                override fun newArray(size: Int): Array<Plant?> = arrayOfNulls(size)
        }
}