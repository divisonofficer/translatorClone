package edu.skku.cs.translatorclone.activity

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.*
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import edu.skku.cs.translatorclone.R
import edu.skku.cs.translatorclone.customview.ClearEditText
import edu.skku.cs.translatorclone.databinding.ActivityTranslatorBinding
import edu.skku.cs.translatorclone.viewmodel.TranslatorViewModel
import org.jsoup.Jsoup
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class TranslatorActivity : BaseActivity<ActivityTranslatorBinding>(R.layout.activity_translator) {


    private val viewModel : TranslatorViewModel by viewModel()

    override fun viewStart() {

        bind{

            setWebview()


            lifecycleOwner = this@TranslatorActivity
            viewmodel = viewModel

            //setKeyListener(root)
            setInputFocus(etInput)

            clRoot.setOnClickListener {
                etInput.run {
                    if(hasFocus())
                        clearInputFocus()
                }
            }


        }
    }




    private fun setWebview(){

        bind {
            vWeb.settings.apply{
                javaScriptEnabled = true


            }

            vWeb.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.d("WEBVIEW   ", "URL : $url")
                    view?.postDelayed({

                        view.loadUrl("javascript:window.Android.parseHtml(document.getElementsByTagName('html')[0].innerHTML);")
                    },500)

                }


            }
            vWeb.webChromeClient = object : WebChromeClient(){

                override fun onPermissionRequest(request: PermissionRequest?) {
                    request?.run {
                        grant(resources)
                    }
                }
            }

            vWeb.addJavascriptInterface(Js(),"Android")
        }
    }




    private fun setInputFocus(input : ClearEditText)
    {
        input.apply{
            setOnHideListener{
                postDelayed({
                    viewModel.setEtFocus(false)
                },50)

            }

            addTextChangedListener {
                viewModel.requestTranslate(it.toString())

                bind {
                    vWeb.loadUrl("https://translate.google.com/?sl=en&tl=ko&text=${it.toString()}&op=translate")
                }
            }

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



    inner class Js{

        @JavascriptInterface
        fun parseHtml(html : String)
        {
            try {
                Jsoup.parse(html).select("span[class=\"Q4iAWc\"]").text().apply {
                    if(isNotEmpty())
                        viewModel.setResultText(this)
                }
            }
            catch (e:Exception)
            {
                e.printStackTrace()
            }
        }
    }


}