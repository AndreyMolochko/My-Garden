package com.ostrovec.mygarden.ui.myplants

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityMyPlantsBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.AddPlantActivity
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity

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

    private lateinit var binding: ActivityMyPlantsBinding
    private lateinit var plantsAdapter: PlantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_my_plants)
        binding.handler = myPlantHandler

        displayRecyclerView()
    }

    private fun displayRecyclerView() {
        binding.myPlantsRecyclerView.layoutManager = LinearLayoutManager(this)
        plantsAdapter = PlantsAdapter(arrayListOf(Plant(0, "Flower", 0, "", "server", 0, 0),
                Plant(0, "Rose", 0, "", "server", 0, 0)))
        binding.myPlantsRecyclerView.adapter = plantsAdapter
    }
}
