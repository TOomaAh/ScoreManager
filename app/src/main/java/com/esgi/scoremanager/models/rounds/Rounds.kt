package com.esgi.scoremanager.models.rounds

import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Rounds(private val moves : MutableList<Move> = mutableListOf())  {

}