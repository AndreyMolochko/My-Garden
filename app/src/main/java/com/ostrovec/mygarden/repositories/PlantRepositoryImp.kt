package com.ostrovec.mygarden.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.ostrovec.mygarden.room.database.AppDatabase
import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlantRepositoryImp(val database: AppDatabase) : PlantRepository {

    companion object {
        const val PLANTS_COLLECTION = "MyGarden/plants"
        const val PLANT_NAME = "name"
        const val PLANT_ID = "id"
        const val PLANT_IRRIGATION_PERIOD = "irrigation period"
        const val PLANT_LOCAL_URL_PHOTO = "local url photo"
    }

    private val remoteDB = FirebaseFirestore.getInstance().document(PLANTS_COLLECTION)

    override fun insertPlant(plant: Plant): Disposable = Observable.fromCallable {
        database.plantDao().insertPlant(plant)
    }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.e("ROOM", "plant was added: subscribe: $it")
            }

    override fun loadPlants(): Flowable<List<Plant>> {
        return database.plantDao().getPlants().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updatePlant(plant: Plant): Completable {
        return Completable.fromCallable {
            database.plantDao().updatePlant(plant)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deletePlant(plant: Plant): Completable {
        return Completable.fromCallable {
            database.plantDao().deletePlant(plant)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addRemotePlant(plant: Plant): Completable {
        return Completable.fromCallable {

            val plantData = HashMap<String, Any>()
            plantData[PLANT_NAME] = plant.name
            plantData[PLANT_ID] = plant.id
            plantData[PLANT_IRRIGATION_PERIOD] = plant.irrigationPeriod
            plantData[PLANT_LOCAL_URL_PHOTO] = plant.urlLocalPhoto
            remoteDB.set(plantData).addOnSuccessListener {
                Log.e("FIREBASREMOTE", "onSuccess")
            }.addOnFailureListener {
                Log.e("FIREBASREMOTE", "onError = ${it.message}")
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteRemotePlant(plant: Plant): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateRemotePlant(plant: Plant): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadRemotePlants(): Flowable<List<Plant>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}