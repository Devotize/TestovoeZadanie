package com.example.testovoezadanie.presentation.helper

import android.content.Context
import android.util.Log
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.example.testovoezadanie.FragmentMainBindingModel_
import com.example.testovoezadanie.R
import com.example.testovoezadanie.fragmentMain
import com.example.testovoezadanie.presentation.fragment.MainFragmentState

class MyEpoxyController(private val context: Context): TypedEpoxyController<MainFragmentState>() {

    var callback: EpoxyController.() -> Unit = {}

    override fun buildModels(data: MainFragmentState?) {
        callback(this)
    }

    fun withModels(buildModels: EpoxyController.() -> Unit) {

        callback = buildModels
        setData(null)

    }




}