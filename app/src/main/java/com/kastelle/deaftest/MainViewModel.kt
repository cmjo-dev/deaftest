package com.kastelle.deaftest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kastelle.deaftest.data.Song

class MainViewModel: ViewModel() {

    private val songs: MutableLiveData<List<Song>> = MutableLiveData()

    init {
        songs.value = listOf(
            Song("Oui ou Non", "Angele", "Brol", "C'est oui\nOu bien\nC'est non"),
            Song("Que du love", "Kiddy Smile", "Brol", "Dipinsa ano saudu"),
            Song("J'entends", "Angele", "Brol", "Avant qu'il parte j'en avais peur")
        )
    }

    fun getSongs(): LiveData<List<Song>> = songs
}