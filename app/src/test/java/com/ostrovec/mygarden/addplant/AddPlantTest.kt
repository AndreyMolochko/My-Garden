package com.ostrovec.mygarden.addplant

import com.ostrovec.mygarden.ui.addplant.AddPlantViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class AddPlantTest {

    lateinit var addPlantViewModel: AddPlantViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        addPlantViewModel = AddPlantViewModel()
    }

    @Test
    fun checkSaveButton(){
        addPlantViewModel.checkSaveButton("Flower","2","images/urlphoto/id/1")
    }

}