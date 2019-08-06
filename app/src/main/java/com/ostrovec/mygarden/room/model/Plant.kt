package com.ostrovec.mygarden.room.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Plants")
data class Plant(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        var name: String?,

        @ColumnInfo(name = "irrigation_period")
        @SerializedName("irrigation_period")
        var irrigationPeriod: Long,

        @ColumnInfo(name = "url_local_photo")
        @SerializedName("url_local_photo")
        var urlLocalPhoto: String?,

        @ColumnInfo(name = "url_server_photo")
        @SerializedName("url_server_photo")
        var urlServerPhoto: String?,

        @ColumnInfo(name = "start_irrigation")
        @SerializedName("start_irrigation")
        var startIrrigation: Long,

        @ColumnInfo(name = "end_irrigation")
        @SerializedName("end_irrigation")
        var endIrrigation: Long) : Parcelable {
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