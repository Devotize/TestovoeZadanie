package com.example.testovoezadanie.presentation.fragment

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import java.util.*

class MainFragmentViewModel(initialState: MainFragmentState): BaseMvRxViewModel<MainFragmentState>(initialState, false) {

    suspend fun getCurrentTime() {
        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val sec = calendar.get(Calendar.SECOND)

        setState { copy(hour = hours, min = min, sec = sec) }

    }
}

data class MainFragmentState(val hour: Int = 0,
                             val min: Int = 0,
                             val sec: Int = 0): MvRxState