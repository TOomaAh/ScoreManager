package com.esgi.scoremanager.models.sport

import android.os.Parcelable
import com.esgi.scoremanager.models.entities.Player

interface Builder : Parcelable {
    fun setName(name: String)
    fun addEntity(entity: Player)
    fun removeEntity(entity: Player)
    fun build(): Sport
}