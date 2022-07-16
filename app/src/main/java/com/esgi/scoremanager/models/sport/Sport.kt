package com.esgi.scoremanager.models.sport

import android.os.Parcelable
import com.esgi.scoremanager.models.entities.Player


abstract class Sport(
    val sportName: String,
    val entity: MutableList<Player>
    ) : Parcelable {

    abstract fun getRule(): String

    fun addEntity(entity: Player) = this.entity.add(entity)

}