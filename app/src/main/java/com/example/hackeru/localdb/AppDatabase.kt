package com.example.hackeru.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/*
@Database(
    entities = [],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): MusicDao

    companion object {

        var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "music_db")
                    .fallbackToDestructiveMigration()
                    .build()
            return instance!!
        }
    }
}
*/
