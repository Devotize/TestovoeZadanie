package com.example.testovoezadanie.presentation.fragment

import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.example.testovoezadanie.presentation.helper.MyEpoxyController
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment() : BaseMvRxFragment() {
    private val epoxyController: MyEpoxyController by lazy {epoxyController()}


    override fun invalidate() {
        epoxyController.requestModelBuild()
    }

    abstract fun epoxyController() : MyEpoxyController

}