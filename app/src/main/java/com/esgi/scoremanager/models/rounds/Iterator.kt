package com.esgi.scoremanager.models.rounds

import kotlinx.android.parcel.Parcelize

@Parcelize
interface Iterator {
    fun hasNext() : Boolean
    fun getNext() : Rounds?
    fun reset()
}