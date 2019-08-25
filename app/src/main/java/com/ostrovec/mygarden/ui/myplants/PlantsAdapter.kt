package com.ostrovec.mygarden.ui.myplants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ostrovec.mygarden.databinding.ItemRecyclerMyPlantsBinding
import com.ostrovec.mygarden.room.model.Plant

class PlantsAdapter(private var plantsList: List<Plant>) : RecyclerView.Adapter<PlantsAdapter
.PlantsHolder>() {

    private lateinit var binding: ItemRecyclerMyPlantsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerMyPlantsBinding.inflate(layoutInflater, parent, false)

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
        }
    }
}