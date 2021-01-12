package com.example.testovoezadanie.presentation.helper

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.testovoezadanie.R
import kotlinx.android.synthetic.main.epoxy_fragment_main.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class FragmentCustomModel @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {
        init {
            inflate(context, R.layout.epoxy_fragment_main, this)
        }

    @TextProp
    fun setHours(hours: CharSequence) {
        hour_text_view.text = context.getString(R.string.hour, hours)
    }

    @TextProp
    fun setMinutes(min: CharSequence) {
        min_text_view.text = context.getString(R.string.sec, min)
    }

    @TextProp
    fun setSeconds(sec: CharSequence) {
        sec_text_view.text = context.getString(R.string.sec, sec)
    }

}