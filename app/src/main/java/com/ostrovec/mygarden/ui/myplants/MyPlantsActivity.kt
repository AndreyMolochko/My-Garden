package com.ostrovec.mygarden.ui.myplants

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityMyPlantsBinding
import com.ostrovec.mygarden.databinding.AlertDialogDeletePlantBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.AddPlantActivity
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.ui.updateplant.UpdatePlantActivity
import io.reactivex.disposables.Disposable

class MyPlantsActivity : BaseNavigationActivity(), PlantsAdapter.ListenerClickUpdate {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MyPlantsActivity::class.java)
            context.startActivity(intent)
        }
    }

    val myPlantHandler: MyPlantsHandler = object : MyPlantsHandler {
        override fun onClickAdd(context: Context) {
            AddPlantActivity.open(this@MyPlantsActivity)
        }
    }

    val deleteDialogHandler: DeleteDialogHandler = object : DeleteDialogHandler {
        override fun onClickYes(plant: Plant) {
            myPlantsViewModel.deletePlant(plant).subscribe {
                Log.e("ondata","plant was deleted")
                deleteAlertDialog.cancel()
            }
            myPlantsViewModel.deleteRemotePlant(plant.id).subscribe {
                Log.e("firebase","plant was deleted")
            }
        }

        override fun onClickCancel() {
            deleteAlertDialog.cancel()
        }
    }

    private lateinit var binding: ActivityMyPlantsBinding
    private lateinit var myPlantsViewModel: MyPlantsViewModel
    private lateinit var plantsAdapter: PlantsAdapter
    private lateinit var deleteAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_my_plants)
        binding.handler = myPlantHandler
        myPlantsViewModel = getViewModel(MyPlantsViewModel::class.java)
        val myPlants: Disposable = myPlantsViewModel.getPlants().subscribe {
            displayRecyclerView(it)
        }
    }

    override fun onClickUpdate(plant: Plant) {
        UpdatePlantActivity.open(this@MyPlantsActivity, plant)
    }

    override fun onClickGarbage(plant: Plant) {
        showDialog(plant)
    }

    private fun showDialog(plant: Plant) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val deleteDialogBinding: AlertDialogDeletePlantBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this@MyPlantsActivity), R.layout.alert_dialog_delete_plant,
                null, false)
        builder.setView(deleteDialogBinding.root)
        deleteDialogBinding.model = plant
        deleteDialogBinding.handler = deleteDialogHandler
        deleteAlertDialog = builder.create()
        deleteAlertDialog?.show()
    }

    private fun displayRecyclerView(plantList: List<Plant>) {
        binding.myPlantsRecyclerView.layoutManager = LinearLayoutManager(this)
        plantsAdapter = PlantsAdapter(this, plantList)
        binding.myPlantsRecyclerView.adapter = plantsAdapter
    }
}
