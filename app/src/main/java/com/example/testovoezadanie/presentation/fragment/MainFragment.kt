package com.example.testovoezadanie.presentation.fragment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.mvrx.*
import com.example.testovoezadanie.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*


class MainFragment : BaseMvRxFragment() {

    private val viewModel: MainFragmentViewModel by fragmentViewModel()


    override fun invalidate() {
        withState(viewModel) {
            hour_text_view.text = context?.getString(R.string.hour, it.hour)
            min_text_view.text = context?.getString(R.string.min, it.min)
            sec_text_view.text = context?.getString(R.string.sec, it.sec)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(IO).launch {
            while(true) {
                viewModel.getCurrentTime()

            }
        }


        toast_button.setOnClickListener {
            val timeStamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().time)

            val toast = Toast.makeText(context, "${context?.getString(R.string.current_time_toast, timeStamp)}", Toast.LENGTH_SHORT)
            val toastView = toast.view
            toastView.setBackgroundResource(R.drawable.toast_button_shape)
            toast.show()
        }

    }




}