package com.kastelle.deaftest.ui.sent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** TODO: add the javadoc. */
class SentTestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is SentTestsFragment"
    }
    val text: LiveData<String> = _text
}