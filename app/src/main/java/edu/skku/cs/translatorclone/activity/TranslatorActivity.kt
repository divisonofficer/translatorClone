package edu.skku.cs.translatorclone.activity


import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import edu.skku.cs.translatorclone.R
import edu.skku.cs.translatorclone.customview.ClearEditText
import edu.skku.cs.translatorclone.databinding.ActivityTranslatorBinding
import edu.skku.cs.translatorclone.viewmodel.TranslatorViewModel
import kotlinx.coroutines.launch



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@AndroidEntryPoint
class TranslatorActivity : BaseActivity<ActivityTranslatorBinding>(R.layout.activity_translator) {


    private val viewModel: TranslatorViewModel by viewModels()

    override fun viewStart() {

        bind {
            setWebview()
            lifecycleOwner = this@TranslatorActivity
            viewmodel = viewModel
            activity = this@TranslatorActivity
            setInputFocus(etInput)
            clRoot.setOnClickListener {
                etInput.run {
                    if (hasFocus())
                        clearInputFocus()
                }
            }
        }

        observe()
    }


    private fun setWebview() {

        bind {
            vWeb.settings.javaScriptEnabled = true
            vWeb.webViewClient = viewModel.webViewClient
            vWeb.webChromeClient = viewModel.webChromeClient
            vWeb.addJavascriptInterface(viewModel.jsInterface, "Android")
        }
    }


    private fun setInputFocus(input: ClearEditText) {
        input.apply {
            setOnFocusChangeListener { view, focus ->
                if (focus)
                    viewModel.setEtFocus(true)
                else
                    hideKeyboard()
                //todo https://code.luasoftware.com/tutorials/android/edittext-clear-focus-on-keyboard-hidden/
            }
        }

    }

    private fun hideKeyboard() {
        currentFocus?.windowToken?.run {
            imm.hideSoftInputFromWindow(this, 0)
        }
    }

    fun requestClearFocus() {
        bind {
            etInput.postDelayed({
                viewModel.setEtFocus(false)
            }, 50)
        }
    }


    private var oldHeight = -1


    private fun clearInputFocus() {

        bind {
            if (etInput.hasFocus()) {
                root.postDelayed({
                    viewModel.setEtFocus(false)
                }, 10)
                hideKeyboard()
                etInput.clearFocus()

            }
        }
    }


    private fun observe() {
        lifecycleScope.launch {
            viewModel.inputText.collect { str ->
                bind {
                    vWeb.loadUrl("https://translate.google.com/?sl=en&tl=ko&text=${str}&op=translate")
                }
            }
        }
    }


}