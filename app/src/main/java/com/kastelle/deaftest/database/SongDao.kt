package com.kastelle.deaftest.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/** Class allowing to interact with the [Song] database table. */
@Dao
interface SongDao {

    /** Get all the songs. */
    @Query("SELECT * FROM song")
    fun getAll(): List<Song>

    /** Insert all the given songs. */
    @Insert
    fun insertAll(vararg songs: Song)

    /** Delete all the given songs. */
    @Delete
    fun delete(song: Song)
}
