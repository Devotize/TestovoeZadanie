package com.example.testovoezadanie.presentation.fragment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.*
import com.example.testovoezadanie.R
import com.example.testovoezadanie.fragmentMain
import com.example.testovoezadanie.presentation.helper.MyEpoxyController
import kotlinx.android.synthetic.main.epoxy_fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*


class MainFragment : BaseMvRxFragment(){

    private val viewModel: MainFragmentViewModel by fragmentViewModel()
    private val epoxyController: MyEpoxyController by lazy{MyEpoxyController(requireContext())}

    private var isFragmentVisible: Boolean = false
    private lateinit var myGetTimeJob: Job


    override fun onPause() {
        super.onPause()
        isFragmentVisible = false
        Log.d("MainFragment", "Is fragment visible: $isFragmentVisible")
        startGetTimeIfVisible()
    }

    override fun onResume() {
        super.onResume()
        isFragmentVisible = true
        Log.d("MainFragment", "Is fragment visible: $isFragmentVisible")
        startGetTimeIfVisible()

    }

    override fun invalidate() {
        withState(viewModel) {
            epoxyController.setData(it)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.epoxy_fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFragmentVisible = true
        myGetTimeJob = CoroutineScope(IO).launch {
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



    private fun startGetTimeIfVisible() {
        if (isFragmentVisible) {
            myGetTimeJob.start()
        } else {
            myGetTimeJob.cancel()
        }
    }


}