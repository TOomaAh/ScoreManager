package com.esgi.scoremanager.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.esgi.scoremanager.models.sport.Bowling

@Database(entities = [GameRoom::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameRoom

    companion object {
        private var INSTANCE: GameDatabase? = null
        fun getDatabase(context: Context): GameDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,GameDatabase::class.java, "bowling_database")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}