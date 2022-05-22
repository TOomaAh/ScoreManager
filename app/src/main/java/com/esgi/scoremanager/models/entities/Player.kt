package com.esgi.scoremanager.models.entities

import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.Move

class Player(val name: String, val moves : MutableList<Move>) : Entity(name) { }