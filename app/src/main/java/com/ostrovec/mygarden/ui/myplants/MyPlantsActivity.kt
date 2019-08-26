package com.ostrovec.mygarden.ui.myplants

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityMyPlantsBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.AddPlantActivity
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import io.reactivex.disposables.Disposable

class MyPlantsActivity : BaseNavigationActivity() {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MyPlantsActivity::class.java)
            context.startActivity(intent)
        }
    }

    val myPlantHandler: MyPlantsHandler = object : MyPlantsHandler {
        override fun onClickAdd(context: Context) {
            AddPlantActivity.open(context)
        }
    }

    val myPlantsItemRecyclerHandler: MyPlantsItemRecyclerHandler = object :
            MyPlantsItemRecyclerHandler {
        override fun clickOnUpdate() {
            Log.e("ONDATA", "click on update")
        }
    }


    private lateinit var binding: ActivityMyPlantsBinding
    private lateinit var myPlantsViewModel: MyPlantsViewModel
    private lateinit var plantsAdapter: PlantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_my_plants)
        binding.handler = myPlantHandler
        myPlantsViewModel = getViewModel(MyPlantsViewModel::class.java)
        val myPlants: Disposable = myPlantsViewModel.getPlants().subscribe {
            displayRecyclerView(it)
        }
    }

    private fun displayRecyclerView(plantList: List<Plant>) {
        binding.myPlantsRecyclerView.layoutManager = LinearLayoutManager(this)
        plantsAdapter = PlantsAdapter(myPlantsItemRecyclerHandler, plantList)
        binding.myPlantsRecyclerView.adapter = plantsAdapter
    }
}
