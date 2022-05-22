package com.esgi.scoremanager.models

abstract class Sport(
    protected val name: String,
    private val entity: MutableList<Entity>,
    private val rounds: List<Round>,
    private val nbrRound: Int) {

    abstract fun getRule(): String

    fun addPlayer(player: Entity) = this.entity.add(player)
    fun getRoundPlayed(): Int = this.rounds.size
    fun isLastRound(): Boolean = this.rounds.size == this.nbrRound

}