package edu.skku.cs.translatorclone.activity

import android.content.res.Resources
import android.util.Log
import android.view.View
import android.widget.EditText
import edu.skku.cs.translatorclone.R
import edu.skku.cs.translatorclone.databinding.ActivityTranslatorBinding
import edu.skku.cs.translatorclone.viewmodel.TranslatorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class TranslatorActivity : BaseActivity<ActivityTranslatorBinding>(R.layout.activity_translator) {


    private val viewModel : TranslatorViewModel by viewModel()

    override fun viewStart() {


        bind{

            lifecycleOwner = this@TranslatorActivity
            viewmodel = viewModel

            setKeyListener(root)
            setInputFocus(etInput)

            clRoot.setOnClickListener {
                etInput.run {
                    if(hasFocus())
                        clearInputFocus()
                }
            }


        }
    }




    private fun setInputFocus(input : EditText)
    {
        input.apply{

            setOnFocusChangeListener { view, focus ->
                if(focus)
                {
                    viewModel.setEtFocus(true)
                }

                if(!focus)
                    hideKeyboard()

                //todo https://code.luasoftware.com/tutorials/android/edittext-clear-focus-on-keyboard-hidden/
            }
        }

    }

    private fun hideKeyboard(){
        currentFocus?.windowToken?.run{
            imm.hideSoftInputFromWindow(this,0)
        }
    }

    private fun onKeyboardHide(){
        bind {
            if(etInput.hasFocus())
            {
                etInput.clearFocus()
            }
            root.postDelayed ({
                viewModel.setEtFocus(false)
            },10)
        }

    }

    private fun onKeyboardShow(){
        viewModel.setEtFocus(true)
    }


    private var oldHeight = -1


    private fun clearInputFocus(){

        bind {
            if(etInput.hasFocus()) {
                root.postDelayed ({
                    viewModel.setEtFocus(false)
                },10)
                hideKeyboard()
                etInput.clearFocus()

            }
        }
    }


    private fun setKeyListener(root : View)
    {
        root.addOnLayoutChangeListener { view, _, _, _, _, _, _, _, _ ->
            Log.d(javaClass.name,"Height : ${view.height} / ${Resources.getSystem().displayMetrics.heightPixels}")
            if(view.height >= Resources.getSystem().displayMetrics.heightPixels * 0.8)
            {
                if(oldHeight < 0)
                {
                    oldHeight = view.height

                }
                else if(oldHeight == view.height) {

                }
                else {
                    Log.d(javaClass.name, "HIDDEN")
                    onKeyboardHide()
                }
            }
            else if(oldHeight > 0 && oldHeight != view.height){
                onKeyboardShow()
            }



            oldHeight = view.height
        }
    }





}