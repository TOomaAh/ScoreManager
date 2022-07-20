package com.esgi.scoremanager.room

import androidx.annotation.WorkerThread
import com.esgi.scoremanager.models.Game

class GameRepository(private val gameRoomDao: GameRoomDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAll() : List<Game> {
        return gameRoomDao.getAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(game: Game) {
        gameRoomDao.insertGame(game)
    }
}