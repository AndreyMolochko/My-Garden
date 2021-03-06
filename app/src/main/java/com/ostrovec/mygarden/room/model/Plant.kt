package com.ostrovec.mygarden.room.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.PropertyName
import com.google.gson.annotations.SerializedName
import com.ostrovec.mygarden.BR
import com.ostrovec.mygarden.ui.sign.model.RemotePlant

@Entity(tableName = "Plants")
data class Plant(

        @PrimaryKey(autoGenerate = true)
        var id: Long,

        var name: String,

        @ColumnInfo(name = "irrigation_period")
        @SerializedName("irrigation period")
        @get:PropertyName("irrigation period")
        @set:PropertyName("irrigation period")
        var irrigationPeriod: Long,

        @ColumnInfo(name = "url_local_photo")
        @SerializedName("url local photo")
        @get:PropertyName("local url photo")
        @set:PropertyName("local url photo")
        var urlLocalPhoto: String,

        @ColumnInfo(name = "url_server_photo")
        @SerializedName("url_server_photo")
        @get:PropertyName("server url photo")
        @set:PropertyName("server url photo")
        var urlServerPhoto: String?,

        @ColumnInfo(name = "start_irrigation")
        @SerializedName("start_irrigation")
        @get:PropertyName("start irrigation period")
        @set:PropertyName("start irrigation period")
        var startIrrigation: Long,

        @ColumnInfo(name = "end_irrigation")
        @SerializedName("end_irrigation")
        @get:PropertyName("end irrigation period")
        @set:PropertyName("end irrigation period")
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

    constructor(): this(
        0,"",0,"","",0,0
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

        fun remotePlantToPlant(remotePlant: RemotePlant):Plant{
            return Plant(
                    remotePlant.id,
                    remotePlant.name,
                    remotePlant.irrigationPeriod,
                    remotePlant.localUrlPhoto,
                    remotePlant.serverUrlPhoto,
                    remotePlant.startIrrigationPeriod,
                    remotePlant.endIrrigationPeriod
            )
        }

        override fun createFromParcel(parcel: Parcel): Plant = Plant(parcel)

        override fun newArray(size: Int): Array<Plant?> = arrayOfNulls(size)
    }
}