package edu.skku.cs.translatorclone.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter


@BindingAdapter("onHide")
fun onHide(view : ClearEditText, listener : ClearEditText.OnHideListener)
{
    view.setOnHideListener(listener)
}


class ClearEditText : AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)


    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode)
        {
            KeyEvent.KEYCODE_BACK ->{
                clearFocus()
                Log.d(javaClass.name,"Listener Info : $onHide")
                onHide?.action()
            }
        }
        return super.onKeyPreIme(keyCode, event)
    }

    fun setOnHideListener(listener: OnHideListener)
    {
        onHide = listener
    }
    fun setOnHideListener(lambda : () -> Unit)
    {
        onHide = object : OnHideListener{
            override fun action() {
                lambda()
            }
        }
    }

    private var onHide : OnHideListener? = null


    interface OnHideListener{
        fun action()
    }
}