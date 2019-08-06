package com.ostrovec.mygarden.repositories

import android.util.Log
import com.ostrovec.mygarden.room.database.AppDatabase
import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlantRepositoryImp (val database: AppDatabase): PlantRepository{

    override fun insertPlant(plant: Plant): Disposable = Observable.fromCallable { database
            .plantDao().insertPlant(plant) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.e("ROOM","plant was added: subscribe: $it")
            }

}