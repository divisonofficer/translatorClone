package edu.skku.cs.translatorclone.viewmodel


import edu.skku.cs.translatorclone.ParseTrans
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TranslatorViewModel : BaseViewModel() {

    private val _etOnFocus = MutableStateFlow(false)
    val etOnFocus : StateFlow<Boolean> get() = _etOnFocus

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
        //_resultText.value = text


    }




}