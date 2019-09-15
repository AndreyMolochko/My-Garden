package com.ostrovec.mygarden.ui.myplants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ostrovec.mygarden.databinding.ItemRecyclerMyPlantsBinding
import com.ostrovec.mygarden.room.model.Plant

class PlantsAdapter(private var callback: PlantsListener, private
var plantsList: List<Plant>) : RecyclerView
.Adapter<PlantsAdapter
.PlantsHolder>() {

    interface PlantsListener {
        fun onClickUpdate(plant: Plant)

        fun onClickGarbage(plant:Plant)
    }

    val myPlantsItemRecyclerHandler: MyPlantsItemRecyclerHandler = object :
            MyPlantsItemRecyclerHandler {
        override fun clickOnUpdate(plant: Plant) {
            callback.onClickUpdate(plant)
        }

        override fun clickOnGarbage(plant: Plant) {
            callback.onClickGarbage(plant)
        }
    }

    private lateinit var binding: ItemRecyclerMyPlantsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerMyPlantsBinding.inflate(layoutInflater, parent, false)
        binding.handler = myPlantsItemRecyclerHandler

        return PlantsHolder(binding)
    }

    override fun getItemCount(): Int {
        return plantsList.size
    }

    override fun onBindViewHolder(holder: PlantsHolder, position: Int) {
        holder.bind(plantsList[position])
    }

    class PlantsHolder(var binging: ItemRecyclerMyPlantsBinding) : RecyclerView.ViewHolder(binging
            .root) {
        fun bind(plant: Plant) {
            binging.itemRecyclerMyPlantsNameTextView.text = plant.name
            binging.model = plant
        }
    }
}