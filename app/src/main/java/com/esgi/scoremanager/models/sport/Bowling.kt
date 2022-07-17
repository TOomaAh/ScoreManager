package com.esgi.scoremanager.models.sport

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import com.esgi.scoremanager.models.iterator.rounds.RoundsIterator
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class Bowling(
    @ColumnInfo var name: String,
    @ColumnInfo var entities : MutableList<Player>,
    @ColumnInfo var rounds : RoundsIterator,
    @PrimaryKey(autoGenerate = true) var id: Int = 0) : Sport(sportName = name,
    entity = entities) {

    override fun getRule(): String = "Merveilleuse regle"

    fun addRound(rounds: Rounds) {
        this.rounds.addItem(rounds)
    }
}