package com.esgi.scoremanager.models.sport

import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.rounds.RoundsIterator
import kotlinx.android.parcel.Parcelize

@Parcelize
abstract class Sport(
    val name: String,
    val entity: MutableList<Entity>,
    val rounds: RoundsIterator,
    ) {

    abstract fun getRule(): String

    fun addEntity(entity: Entity) = this.entity.add(entity)
    fun getRoundPlayed(): Int = this.rounds.index
    fun isLastRound(): Boolean = this.rounds.index == this.rounds.maxRounds

}