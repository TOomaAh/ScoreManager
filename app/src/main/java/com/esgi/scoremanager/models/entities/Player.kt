package com.esgi.scoremanager.models.entities

import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.Move

class Player(private var name: String, val moves : MutableList<Move>) : Entity(name) {
    override fun setName(name: String){
        this.name = name
    }

    override fun getName() : String {
        return this.name
    }
}