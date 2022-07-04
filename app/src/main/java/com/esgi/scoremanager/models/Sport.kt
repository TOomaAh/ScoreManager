package com.esgi.scoremanager.models

abstract class Sport(
    val name: String,
    val entity: MutableList<Entity>,
    val rounds: List<Round>,
    var nbrRound: Int
    ) {

    abstract fun getRule(): String

    fun addEntity(entity: Entity) = this.entity.add(entity)
    fun getRoundPlayed(): Int = this.rounds.size
    fun isLastRound(): Boolean = this.rounds.size == this.nbrRound

}