package com.example.testovoezadanie.presentation.fragment

import android.util.Log
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import kotlinx.coroutines.delay
import java.util.*

class MainFragmentViewModel(initialState: MainFragmentState): BaseMvRxViewModel<MainFragmentState>(initialState, false) {

    suspend fun getCurrentTime() {
        delay(1000)
        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val sec = calendar.get(Calendar.SECOND)
//        Log.d("MainFragment", "GetCurrentTime")

        setState { copy(hour = hours, min = min, sec = sec) }

    }
}

data class MainFragmentState(val hour: Int = 0,
                             val min: Int = 0,
                             val sec: Int = 0): MvRxState