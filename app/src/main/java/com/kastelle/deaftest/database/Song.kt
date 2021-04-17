package com.kastelle.deaftest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** Represents the database table containing the songs. */
@Entity
data class Song(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "artist") val artist: String?,
    @ColumnInfo(name = "album") val album: String?,
    @ColumnInfo(name = "lyrics") val lyrics: String?
)
