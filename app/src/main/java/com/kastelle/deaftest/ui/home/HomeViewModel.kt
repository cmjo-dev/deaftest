package com.kastelle.deaftest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** TODO: add the javadoc. */
class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is HomeFragment"
    }
    val text: LiveData<String> = _text
}