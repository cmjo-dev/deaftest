package com.kastelle.deaftest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kastelle.deaftest.data.Song

class MainViewModel: ViewModel() {

    private val songs: MutableLiveData<List<Song>> = MutableLiveData()

    init {
        songs.value = listOf(Song("Oui ou Non", "Angèle", "Brol", "\nC'est oui\nOu bien\nC'est non"))
    }

    fun getSongs(): LiveData<List<Song>> = songs
}