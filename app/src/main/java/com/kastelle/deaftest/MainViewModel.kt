package com.kastelle.deaftest

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.kastelle.deaftest.database.AppDatabase
import com.kastelle.deaftest.database.Song

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val songs: MutableLiveData<List<Song>> = MutableLiveData()

    init {
        val db = AppDatabase.getInstance(getApplication<Application>().applicationContext)
        songs.value = db.songDao().getAll()
    }

    fun getSongs(): LiveData<List<Song>> = songs
}