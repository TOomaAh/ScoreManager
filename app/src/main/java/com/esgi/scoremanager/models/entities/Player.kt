package com.esgi.scoremanager.models.entities

import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player(var name: String, val moves : MutableList<Move> = mutableListOf()) : Entity(name) {

}