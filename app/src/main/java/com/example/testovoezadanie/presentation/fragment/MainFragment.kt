package com.example.testovoezadanie.presentation.fragment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.*
import com.example.testovoezadanie.R
import com.example.testovoezadanie.fragmentMain
import com.example.testovoezadanie.presentation.helper.MyEpoxyController
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*


class MainFragment : BaseFragment() {

//    private val viewModel: MainFragmentViewModel by fragmentViewModel()

    private val viewModel: MainFragmentViewModel by fragmentViewModel()

//    override fun invalidate() {
////        withState(viewModel) {
////            hour_text_view.text = context?.getString(R.string.hour, it.hour)
////            min_text_view.text = context?.getString(R.string.min, it.min)
////            sec_text_view.text = context?.getString(R.string.sec, it.sec)
////        }
//        epoxyController.requestModelBuild()
//    }

    override fun epoxyController() = controller(viewModel) {state ->
        fragmentMain {
            id("fragment")
            Log.d("MainFragment", "State: ${state.hour}")
            hours(context?.getString(R.string.hour, state.hour))
            min(context?.getString(R.string.min, state.min))
            sec(context?.getString(R.string.sec, state.sec))

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
        viewModel.subscribe {
            Log.d("MainFragment", "Subscribed: $it")
            hour_text_view.text = context?.getString(R.string.hour, it.hour)
            min_text_view.text = context?.getString(R.string.min, it.min)
            sec_text_view.text = context?.getString(R.string.sec, it.sec)
        }


        toast_button.setOnClickListener {
            val timeStamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().time)

            val toast = Toast.makeText(context, "${context?.getString(R.string.current_time_toast, timeStamp)}", Toast.LENGTH_SHORT)
            val toastView = toast.view
            toastView.setBackgroundResource(R.drawable.toast_button_shape)
            toast.show()
        }

    }

    private fun  controller(viewModel: MainFragmentViewModel, buildModels: EpoxyController.(state: MainFragmentState) -> Unit) = MyEpoxyController {
        withState(viewModel) {

            buildModels(it)
        }
    }


}