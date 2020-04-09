package com.kastelle.deaftest.ui.received

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** TODO: add the javadoc. */
class ReceivedTestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ReceivedTestsFragment"
    }
    val text: LiveData<String> = _text
}