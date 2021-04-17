package com.kastelle.deaftest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** The view model handling data to present on the home UI. */
class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is HomeFragment"
    }
    val text: LiveData<String> = _text
}