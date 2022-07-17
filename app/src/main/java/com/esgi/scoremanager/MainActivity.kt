package com.esgi.scoremanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.esgi.scoremanager.models.Game
import com.esgi.scoremanager.room.GameDatabase
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val games: List<Game> = GameDatabase.getDatabase(applicationContext).gameDao().getAll()

        }catch (e : Exception) {
            Log.d("TAG", "onCreate: ${e.message}")
        }

        setContentView(R.layout.activity_main)
    }
}