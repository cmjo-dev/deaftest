package com.kastelle.deaftest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Represents the local database of the app.
 * Via its single instance, the clients can access the data DAO allowing to interact with the
 * database tables. Queries to the database instance or to its DAOs should never be performed on the
 * UI thread.
 */
@Database(entities = [Song::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        /** Single instance of the database. */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Get the instance allowing to interact with the database and its DAOs.
         * @param context the context used to build the database instance (the application context
         * will be retrieved from it).
         */
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    // TODO: Main thread queries should not be allowed.
                    // TODO: Migration should not be destructive.
                    instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, "songs.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    /** Get the DAO allowing to interact with the database table containing the [Song]. */
    abstract fun songDao(): SongDao
}
