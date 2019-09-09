package com.ostrovec.mygarden.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.ostrovec.mygarden.room.database.AppDatabase
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.room.model.Plant.CREATOR.remotePlantToPlant
import com.ostrovec.mygarden.ui.sign.model.RemotePlant
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class PlantRepositoryImp(val database: AppDatabase) : PlantRepository {

    private val remoteDB = FirebaseFirestore.getInstance()
    private lateinit var uid: String

    init {
        if (FirebaseAuth.getInstance().currentUser != null) {
            uid = FirebaseAuth.getInstance().currentUser!!.uid
        }
    }

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

    override fun deleteRemotePlant(plantId: Long): Completable {
        return Completable.fromCallable {
            remoteDB.document("MyGarden/${uid}/plants/${plantId}/").delete().addOnSuccessListener {
                Log.e("FIREBASREMOTE", "DELETEonSuccess")
            }.addOnFailureListener {
                Log.e("FIREBASREMOTE", "DELETEonError = ${it.message}")
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateRemotePlant(plant: Plant): Completable {
        return Completable.fromCallable {
            remoteDB.document("MyGarden/${uid}/plants/${plant.id}/").update(
                    Plant.PLANT_NAME, plant.name,
                    Plant.PLANT_LOCAL_URL_PHOTO, plant.urlLocalPhoto,
                    Plant.PLANT_SERVER_URL_PHOTO, plant.urlServerPhoto,
                    Plant.PLANT_IRRIGATION_PERIOD, plant.irrigationPeriod,
                    Plant.PLANT_START_IRRIGATION, plant.startIrrigation,
                    Plant.PLANT_END_IRRIGATION, plant.endIrrigation)
                    .addOnSuccessListener {
                        Log.e("FIREBASREMOTE", "UpdateOnSuccess")
                    }.addOnFailureListener {
                        Log.e("FIREBASREMOTE", "UpdateOnError = ${it.message}")
                    }
        }
    }

    override fun loadRemotePlants(): Single<List<Plant>> {

        return Single.create<List<DocumentSnapshot>> { emitter ->
            remoteDB.collection("MyGarden/${uid}/plants").get()
                    .addOnFailureListener {
                        Log.e("FIRESTORELOAD", it.message)
                    }.addOnSuccessListener { result ->
                        //val remotePlant = document.toObject(RemotePlant::class.java)
                        Log.e("firestore","before1 on success")
                        if (!emitter.isDisposed) {
                            Log.e("firestore","before2 on success")
                            emitter.onSuccess(result.documents)
                        }
                    }.addOnCompleteListener {
                        Log.e("firestore","on complete")
                    }.addOnCanceledListener {
                        Log.e("firestore","on canceled")
                    }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapObservable { Observable.fromIterable(it) }
                .map(::mapDocumentToRemotePlant)
                .map(::remotePlantToPlant)
                .toList()

    }

    private val changeObservable = BehaviorSubject.create<List<DocumentSnapshot>> { emitter: ObservableEmitter<List<DocumentSnapshot>> ->
        val listeningRegistration = remoteDB.collection("MyGarden/${uid}/plants")
                .addSnapshotListener { value, error ->
                    if (value == null || error != null) {
                        return@addSnapshotListener
                    }

                    if (!emitter.isDisposed) {
                        emitter.onNext(value.documents)
                    }

                    value.documentChanges.forEach {
                        Log.d("FirestoreTaskRepository", "Data changed type ${it.type} document ${it.document.id}")
                    }
                }

        emitter.setCancellable { listeningRegistration.remove() }
    }

    override fun getChangeObservable(): Observable<List<Plant>> =
            changeObservable.hide()
                    .observeOn(Schedulers.io())
                    .map { list -> list.map(::mapDocumentToRemotePlant).map(::remotePlantToPlant) }

    private fun mapDocumentToRemotePlant(document: DocumentSnapshot) = document.toObject(RemotePlant::class.java)!!.apply {}


}