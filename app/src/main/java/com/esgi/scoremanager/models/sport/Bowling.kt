package com.esgi.scoremanager.models.sport

import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import com.esgi.scoremanager.models.iterator.rounds.RoundsIterator
import kotlinx.android.parcel.Parcelize

@Parcelize
class Bowling(var name: String, var entities : MutableList<Player>, var rounds : RoundsIterator) : Sport(sportName = name,
    entity = entities) {

    override fun getRule(): String = "Merveilleuse regle"

    fun addRound(rounds: Rounds) {
        this.rounds.addItem(rounds)
    }
}