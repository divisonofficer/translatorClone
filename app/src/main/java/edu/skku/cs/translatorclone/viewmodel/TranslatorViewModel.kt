package edu.skku.cs.translatorclone.viewmodel


import android.util.Log
import android.webkit.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jsoup.Jsoup
import javax.inject.Inject


@HiltViewModel
class TranslatorViewModel @Inject constructor(): BaseViewModel() {

    private val _etOnFocus = MutableStateFlow(false)
    val etOnFocus : StateFlow<Boolean> get() = _etOnFocus
    private val _inputText = MutableStateFlow("")
    val inputText : StateFlow<String> get() = _inputText
    private val _resultText = MutableStateFlow("")
    val resultText : StateFlow<String> get() = _resultText


    fun setEtFocus(status : Boolean)
    {

        _etOnFocus.value = status

    }

    fun setResultText(text : String)
    {
        _resultText.value = text
    }

    fun requestTranslate(text : String)
    {
        _inputText.value = text


    }


    val webViewClient = object : WebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.d("WEBVIEW   ", "URL : $url")
            view?.postDelayed({

                view.loadUrl("javascript:window.Android.parseHtml(document.getElementsByTagName('html')[0].innerHTML);")
            },1000)

        }


    }

    val webChromeClient = object : WebChromeClient(){

        override fun onPermissionRequest(request: PermissionRequest?) {
            request?.run {
                grant(resources)
            }
        }
    }

    val jsInterface = Js()

    inner class Js{

        @JavascriptInterface
        fun parseHtml(html : String)
        {
            try {
                Jsoup.parse(html).select("span[class=\"Q4iAWc\"]").text().apply {
                    if(isNotEmpty())
                        setResultText(this)
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }


}