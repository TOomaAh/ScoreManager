package com.esgi.scoremanager.models.rounds

import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize

@Parcelize
class Throws(val mutableList: MutableList<Move>) : Rounds(mutableList) {
}