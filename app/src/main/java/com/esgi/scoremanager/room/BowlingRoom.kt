package com.esgi.scoremanager.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.esgi.scoremanager.models.Game

@Dao
interface GameRoom {

    @Query("SELECT * FROM game")
    fun getAll() : List<Game>

    @Query("SELECT * FROM game WHERE game.id IS :id")
    fun getOne(id: Int) : Game

    @Insert
    fun insertGame(game: Game)
}