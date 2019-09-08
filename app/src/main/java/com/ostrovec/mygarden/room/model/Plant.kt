package com.ostrovec.mygarden.room.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ostrovec.mygarden.BR

@Entity(tableName = "Plants")
data class Plant(

        @PrimaryKey(autoGenerate = true)
        var id: Long,

        var name: String,

        @ColumnInfo(name = "irrigation_period")
        @SerializedName("irrigation_period")
        var irrigationPeriod: Long,

        @ColumnInfo(name = "url_local_photo")
        @SerializedName("url_local_photo")
        var urlLocalPhoto: String,

        @ColumnInfo(name = "url_server_photo")
        @SerializedName("url_server_photo")
        var urlServerPhoto: String?,

        @ColumnInfo(name = "start_irrigation")
        @SerializedName("start_irrigation")
        var startIrrigation: Long,

        @ColumnInfo(name = "end_irrigation")
        @SerializedName("end_irrigation")
        var endIrrigation: Long) : BaseObservable(), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong()
    )

    var setName: String
        @Bindable
        get() = name
        set(value) {
            name = value
            notifyPropertyChanged(BR.setName)
        }

    var setIrrigationPeriod: Long
        @Bindable
        get() = irrigationPeriod
        set(value) {
            irrigationPeriod = value
            notifyPropertyChanged(BR.setIrrigationPeriod)
        }

    var setUrlLocalPhoto: String
        @Bindable
        get() = urlLocalPhoto
        set(value) {
            urlLocalPhoto = value
            notifyPropertyChanged(BR.setUrlLocalPhoto)
        }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeLong(irrigationPeriod)
        parcel.writeString(urlLocalPhoto)
        parcel.writeString(urlServerPhoto)
        parcel.writeLong(startIrrigation)
        parcel.writeLong(endIrrigation)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Plant> {

        const val PLANT_NAME = "name"
        const val PLANT_ID = "id"
        const val PLANT_IRRIGATION_PERIOD = "irrigation period"
        const val PLANT_LOCAL_URL_PHOTO = "local url photo"
        const val PLANT_SERVER_URL_PHOTO = "server url photo"
        const val PLANT_START_IRRIGATION = "start irrigation period"
        const val PLANT_END_IRRIGATION = "end irrigation period"

        fun plantToHashMap(plant: Plant): HashMap<String, Any?> {
            val plantData = HashMap<String, Any?>()
            plantData[PLANT_NAME] = plant.name
            plantData[PLANT_ID] = plant.id
            plantData[PLANT_IRRIGATION_PERIOD] = plant.irrigationPeriod
            plantData[PLANT_LOCAL_URL_PHOTO] = plant.urlLocalPhoto
            plantData[PLANT_SERVER_URL_PHOTO] = plant.urlServerPhoto
            plantData[PLANT_START_IRRIGATION] = plant.startIrrigation
            plantData[PLANT_END_IRRIGATION] = plant.endIrrigation

            return plantData
        }

        fun hashMapToPlant(data: HashMap<String,Any?>):Plant{
            hashMapOf<String,Any?>()
            return Plant(
                    data[PLANT_ID] as Long,
                    data[PLANT_NAME] as String,
                    data[PLANT_IRRIGATION_PERIOD] as Long,
                    data[PLANT_LOCAL_URL_PHOTO] as String,
                    data[PLANT_SERVER_URL_PHOTO] as String?,
                    data[PLANT_START_IRRIGATION] as Long,
                    data[PLANT_END_IRRIGATION] as Long
            )
        }

        override fun createFromParcel(parcel: Parcel): Plant = Plant(parcel)

        override fun newArray(size: Int): Array<Plant?> = arrayOfNulls(size)
    }
}