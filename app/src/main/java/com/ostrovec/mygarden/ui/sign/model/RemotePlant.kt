package com.ostrovec.mygarden.ui.sign.model

import com.google.firebase.firestore.PropertyName

data class RemotePlant(var id: Long = 0,
                       var name: String = "",

                       @get:PropertyName("local url photo")
                       @set:PropertyName("local url photo")
                       var localUrlPhoto: String = "",

                       @get:PropertyName("server url photo")
                       @set:PropertyName("server url photo")
                       var serverUrlPhoto: String = "",

                       @get:PropertyName("irrigation period")
                       @set:PropertyName("irrigation period")
                       var irrigationPeriod: Long = 0,

                       @get:PropertyName("start irrigation period")
                       @set:PropertyName("start irrigation period")
                       var startIrrigationPeriod: Long = 0,

                       @get:PropertyName("end irrigation period")
                       @set:PropertyName("end irrigation period")
                       var endIrrigationPeriod: Long = 0)