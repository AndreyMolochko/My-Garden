package com.ostrovec.mygarden.ui.myplants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ostrovec.mygarden.databinding.ItemRecyclerMyPlantsBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.updateplant.UpdatePlantActivity

class PlantsAdapter(private var callback: ListenerClickUpdate, private
var plantsList: List<Plant>) : RecyclerView
.Adapter<PlantsAdapter
.PlantsHolder>() {

    interface ListenerClickUpdate {
        fun onClickUpdate(plant: Plant)
    }

    val myPlantsItemRecyclerHandler: MyPlantsItemRecyclerHandler = object :
            MyPlantsItemRecyclerHandler {
        override fun clickOnUpdate(plant: Plant) {
            callback.onClickUpdate(plant)
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