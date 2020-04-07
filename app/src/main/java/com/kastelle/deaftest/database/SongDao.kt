package com.kastelle.deaftest.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SongDao {

    @Query("SELECT * FROM song")
    fun getAll(): List<Song>

    @Query("SELECT * FROM song WHERE uid IN (:songIds)")
    fun loadAllByIds(songIds: IntArray): List<Song>

    @Query("SELECT * FROM song WHERE title LIKE :first AND artist LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Song

    @Insert
    fun insertAll(vararg songs: Song)

    @Delete
    fun delete(song: Song)
}
