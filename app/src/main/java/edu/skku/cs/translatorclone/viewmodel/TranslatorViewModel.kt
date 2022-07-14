package edu.skku.cs.translatorclone.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class TranslatorViewModel : BaseViewModel() {

    private val _etOnFocus = MutableStateFlow(false)
    val etOnFocus : StateFlow<Boolean> get() = _etOnFocus


    fun setEtFocus(status : Boolean)
    {

        _etOnFocus.value = status

    }

}