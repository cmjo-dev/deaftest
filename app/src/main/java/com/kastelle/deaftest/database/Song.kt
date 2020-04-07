package com.kastelle.deaftest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** Class representing a song. */
/*data class Song (val title: String, val artist: String, val album: String, val lyrics: String) {
    override fun toString(): String {
        return "$title - $artist ($album)\n$lyrics"
    }
}*/

@Entity
data class Song(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "artist") val artist: String?,
    @ColumnInfo(name = "album") val album: String?,
    @ColumnInfo(name = "lyrics") val lyrics: String?
)
