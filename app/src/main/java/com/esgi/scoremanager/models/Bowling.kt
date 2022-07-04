package com.esgi.scoremanager.models

import com.esgi.scoremanager.models.entities.Player

object Bowling : Sport("Bowling", mutableListOf(), listOf(), 10) {

    private var bowling: Bowling? = null

    override fun getRule(): String = "Merveilleuse regle"

    fun getInstance() : Bowling {
        if (bowling == null){
            bowling = Bowling
        }
        return bowling!!
    }

    fun resetGame() {
        bowling = Bowling
    }




}