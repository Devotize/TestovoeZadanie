package com.example.testovoezadanie.presentation.helper

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.example.testovoezadanie.FragmentMainBindingModel_

class MyEpoxyController(val buildModelsCallback: EpoxyController.() -> Unit = {}): EpoxyController() {
//    @AutoModel
//    lateinit var fragmentModel: FragmentMainBindingModel_

    override fun buildModels() {
        buildModelsCallback()
    }
}