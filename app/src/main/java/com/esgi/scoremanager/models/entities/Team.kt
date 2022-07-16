package com.esgi.scoremanager.models.entities

import com.esgi.scoremanager.models.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team(var name : String, val players : MutableList<Player>) : Entity(name) {
}