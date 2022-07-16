package com.esgi.scoremanager.models.iterator.rounds

import android.os.Parcel
import android.os.Parcelable
import com.esgi.scoremanager.models.Move
import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.iterator.IteratorITem
import kotlinx.android.parcel.Parcelize


@Parcelize
open class Rounds(val players: MutableList<Player>) : IteratorITem() {

    var currentPlayerIndex: Int = 0

    override fun toString(): String {
        return "Rounds(players=$players, currentPlayerIndex=$currentPlayerIndex)"
    }


}