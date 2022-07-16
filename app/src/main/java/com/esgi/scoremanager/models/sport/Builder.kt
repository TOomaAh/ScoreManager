package com.esgi.scoremanager.models.sport

import android.os.Parcelable
import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.rounds.Rounds
import kotlinx.android.parcel.Parcelize

interface Builder : Parcelable {
    fun setName(name: String)
    fun addEntity(entity: Entity)
    fun removeEntity(entity: Entity)
    fun addRound(rounds: Rounds)
    fun build(): Sport
}