package com.ostrovec.mygarden.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ostrovec.mygarden.room.database.AppDatabase
import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlantRepositoryImp(val database: AppDatabase) : PlantRepository {

    companion object {

    }

    private val remoteDB = FirebaseFirestore.getInstance()
    override fun insertPlant(plant: Plant): Flowable<Long> {
        return Flowable.fromCallable<Long> {
            database.plantDao().insertPlant(plant)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            remoteDB.document("MyGarden/${uid}/plants/${plant.id}/")
                    .set(Plant.plantToHashMap(plant))
                    .addOnSuccessListener {
                        Log.e("FIREBASREMOTE", "onSuccess")
                    }.addOnFailureListener {
                        Log.e("FIREBASREMOTE", "onError = ${it.message}")
                    }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteRemotePlant(plantId: Int): Completable {
        return Completable.fromCallable {
            remoteDB.collection("plants").document(plantId.toString()).delete().addOnSuccessListener {
                Log.e("FIREBASREMOTE", "DELETEonSuccess")
            }.addOnFailureListener {
                Log.e("FIREBASREMOTE", "DELETEonError = ${it.message}")
            }
        }
    }

    override fun updateRemotePlant(plant: Plant): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadRemotePlants(): Flowable<List<Plant>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}