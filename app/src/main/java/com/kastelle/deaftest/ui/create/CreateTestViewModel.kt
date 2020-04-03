package com.kastelle.deaftest.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** TODO: add the javadoc. */
class CreateTestViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the CreateTestFragment"
    }
    val text: LiveData<String> = _text
}