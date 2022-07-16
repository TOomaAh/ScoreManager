package com.esgi.scoremanager.models.sport

import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.rounds.Rounds
import com.esgi.scoremanager.models.rounds.RoundsIterator
import kotlinx.android.parcel.Parcelize

@Parcelize
class Bowling(name: String, entities : MutableList<Entity>, rounds : MutableList<Rounds?>) : Sport(name = name,
    entity = entities,
    rounds = RoundsIterator(rounds, 10)) {
    override fun getRule(): String = "Merveilleuse regle"
}