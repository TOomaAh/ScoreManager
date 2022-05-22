package com.esgi.scoremanager.models

abstract class Sport(
    protected val name: String,
    protected val entity: MutableList<Entity>,
    protected val rounds: List<Round>,
    protected val nbrRound: Int) {

    abstract fun getRule(): String

    fun addEntity(entity: Entity) = this.entity.add(entity)
    fun getRoundPlayed(): Int = this.rounds.size
    fun isLastRound(): Boolean = this.rounds.size == this.nbrRound

}