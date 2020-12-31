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


    override fun buildModels(data: MainFragmentState?) {
        fragmentMain {
            id(0)
            hours(context.getString(R.string.hour, data?.hour))
            sec(context.getString(R.string.sec, data?.sec))
            min(context.getString(R.string.min, data?.min))

        }

    }
}