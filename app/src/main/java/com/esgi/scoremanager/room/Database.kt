package com.esgi.scoremanager.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.esgi.scoremanager.models.DateConverter
import com.esgi.scoremanager.models.Game
import com.esgi.scoremanager.models.MoveConverter
import com.esgi.scoremanager.models.entities.PlayerConverter
import com.esgi.scoremanager.models.entities.PlayerListConverter
import com.esgi.scoremanager.models.iterator.rounds.RoundConverter
import com.esgi.scoremanager.models.iterator.rounds.RoundListConverter
import com.esgi.scoremanager.models.sport.Bowling


@Database(entities = [Game::class], version = 2, exportSchema = false)
@TypeConverters(RoundConverter::class,
    RoundListConverter::class,
    PlayerListConverter::class,
    PlayerConverter::class,
    DateConverter::class
)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun bowlingDao(): GameRoomDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameRoomDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}