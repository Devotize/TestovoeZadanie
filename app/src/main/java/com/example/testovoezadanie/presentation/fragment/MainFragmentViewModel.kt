package com.example.testovoezadanie.presentation.fragment

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.widget.Toast
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.example.testovoezadanie.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import java.util.*
import java.util.concurrent.TimeUnit

class MainFragmentViewModel(initialState: MainFragmentState): BaseMvRxViewModel<MainFragmentState>(initialState, false) {

    companion object{
        private const val TAG = "MainFragmentViewModel"
    }

    val disposables = CompositeDisposable()

//    operator fun invoke() = getCurrentTime()

    init {
        getCurrentTime()
    }


    private fun getCurrentTime() {
        Log.d(TAG, "getCurrentTime: called")
        val observable = Observable
            .interval(500, TimeUnit.MILLISECONDS)
            .map {
                val calendar = Calendar.getInstance()
                val hours = calendar.get(Calendar.HOUR_OF_DAY)
                val min = calendar.get(Calendar.MINUTE)
                val sec = calendar.get(Calendar.SECOND)
                MainFragmentState(hours, min, sec)
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        disposables.add(observable.subscribe {
            Log.d(TAG, "onNext: ${it.sec}")
            setState { copy(it.hour, it.min, it.sec) }
        })
    }

    fun showToastWithTime(context: Context) {
        val timeStamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().time)

        val toast = Toast.makeText(context,
            context.getString(R.string.current_time_toast, timeStamp), Toast.LENGTH_SHORT)
        val toastView = toast.view
        toastView.setBackgroundResource(R.drawable.toast_button_shape)
        toast.show()
    }

}

data class MainFragmentState(val hour: Int = 0,
                             val min: Int = 0,
                             val sec: Int = 0): MvRxState