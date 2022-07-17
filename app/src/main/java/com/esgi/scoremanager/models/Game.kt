package com.esgi.scoremanager.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import com.esgi.scoremanager.models.sport.Sport
import java.util.*

@Entity
class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo private val name: String,
    @ColumnInfo private val rounds: List<Rounds?>) {
}