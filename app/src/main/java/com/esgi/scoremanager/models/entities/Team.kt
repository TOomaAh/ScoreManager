package com.esgi.scoremanager.models.entities

import com.esgi.scoremanager.models.Entity

class Team(private var name : String, val players : MutableList<Player> ) : Entity(name) {
    override fun setName(name: String){
        this.name = name
    }

    override fun getName() : String {
        return this.name
    }
}