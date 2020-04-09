package com.kastelle.deaftest.ui.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kastelle.deaftest.database.AppDatabase
import com.kastelle.deaftest.database.Song

/** TODO: add the javadoc. */
class CreateTestViewModel(application: Application) : AndroidViewModel(application) {

    private val songs: MutableLiveData<List<Song>> = MutableLiveData()

    init {
        val db = AppDatabase.getInstance(getApplication<Application>().applicationContext)
        songs.value = db.songDao().getAll()
    }

    fun getSongs(): LiveData<List<Song>> = songs
}