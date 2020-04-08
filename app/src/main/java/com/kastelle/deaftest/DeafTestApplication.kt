package com.kastelle.deaftest

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.google.gson.Gson
import com.kastelle.deaftest.database.AppDatabase
import com.kastelle.deaftest.database.Song
import java.io.BufferedReader


class DeafTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val db = AppDatabase.getInstance(applicationContext)
        db.clearAllTables()

        val songs: ArrayList<Song> = ArrayList()

        val bufferedReader: BufferedReader =  assets.open("lyrics.jsonl").bufferedReader()
        bufferedReader.forEachLine{ songs.add(Gson().fromJson(it, Song::class.java)) }
        db.songDao().insertAll(*songs.toTypedArray())
    }
}