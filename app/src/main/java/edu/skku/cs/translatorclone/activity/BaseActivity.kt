package edu.skku.cs.translatorclone.activity

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import edu.skku.cs.translatorclone.R

abstract class BaseActivity<T : ViewDataBinding>(private val layoutId : Int) : AppCompatActivity() {


    private lateinit var binding : T

    val imm get() = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.background_0)

        binding = DataBindingUtil.setContentView(this,layoutId)

        Log.d(TAG,"OnCreate")
        viewStart()

    }

    abstract fun viewStart()


    protected fun bind(binder : T.()->Unit)
        = binder(binding)


    companion object{
        private const val TAG = "BASEACTIVITY"
    }
}