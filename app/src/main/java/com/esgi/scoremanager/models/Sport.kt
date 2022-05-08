package com.esgi.scoremanager.models

abstract class Sport(
    protected val name: String,
    protected val player: List<Player>,
    protected val score: Score) {

    abstract fun getRule(): String

}