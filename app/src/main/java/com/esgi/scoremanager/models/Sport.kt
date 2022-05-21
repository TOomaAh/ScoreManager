package com.esgi.scoremanager.models

abstract class Sport(
    protected val name: String,
    protected val player: MutableList<Entity>,
    protected val rounds: List<Round>,
    private val nbrRound: Int) {

    abstract fun getRule(): String

    fun addPlayer(player: Entity) = this.player.add(player)

    fun getRoundPlayed(): Int = this.rounds.size
    fun isLastRound(): Boolean = this.rounds.size == this.nbrRound

}