package com.esgi.scoremanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.esgi.scoremanager.room.GameRepository
import com.esgi.scoremanager.room.GameRoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val database by lazy { GameRoomDatabase.getDatabase(this) }
            val repository by lazy { GameRepository(database.bowlingDao()) }

            GlobalScope.launch {
                Log.d("TAG", "onCreate: ${repository.getAll()}")
            }


        }catch (e : Exception) {
            Log.d("TAG", "onCreate: ${e.message}")
        }

        setContentView(R.layout.activity_main)
    }
}