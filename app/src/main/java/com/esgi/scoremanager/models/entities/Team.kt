package com.esgi.scoremanager.models.entities

import com.esgi.scoremanager.models.Entity

class Team(val name : String, val players : MutableList<Player> ) : Entity(name) {}