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



    override fun invalidate() {
        withState(viewModel) {
            epoxyController.withModels {
                fragmentMain {
                    id("SomeId")
                    it.hour
                }

            }
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
//        viewModel.invoke()

        toast_button.setOnClickListener {
            viewModel.showToastWithTime(requireContext())
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposables.clear()
    }


}