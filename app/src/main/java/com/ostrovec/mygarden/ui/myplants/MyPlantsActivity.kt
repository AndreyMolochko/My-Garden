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
import com.ostrovec.mygarden.ui.updateplant.UpdatePlantActivity
import io.reactivex.disposables.Disposable

class MyPlantsActivity : BaseNavigationActivity(),PlantsAdapter.ListenerClickUpdate {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MyPlantsActivity::class.java)
            context.startActivity(intent)
        }
    }

    val myPlantHandler: MyPlantsHandler = object : MyPlantsHandler {
        override fun onClickAdd(context: Context) {

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

    override fun onClickUpdate(plant:Plant) {
        UpdatePlantActivity.open(this@MyPlantsActivity,plant)
    }

    private fun displayRecyclerView(plantList: List<Plant>) {
        binding.myPlantsRecyclerView.layoutManager = LinearLayoutManager(this)
        plantsAdapter = PlantsAdapter(this, plantList)
        binding.myPlantsRecyclerView.adapter = plantsAdapter
    }
}
