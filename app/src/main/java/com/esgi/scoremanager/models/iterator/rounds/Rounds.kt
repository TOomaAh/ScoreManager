package com.esgi.scoremanager.models.iterator.rounds

import android.os.Parcel
import android.os.Parcelable
import com.esgi.scoremanager.models.entities.Player
import kotlinx.android.parcel.Parcelize



open class Rounds() : Parcelable {

    var players : MutableList<Player> = mutableListOf()
    var currentPlayerIndex: Int = 0

    constructor(parcel: Parcel) : this() {
        currentPlayerIndex = parcel.readInt()
    }

    constructor(players: MutableList<Player>) : this() {
        this.players = players.toMutableList()
        for (i in this.players) {
            i.resetAll()
        }
    }



    override fun toString(): String {
        return "Rounds(players=$players, currentPlayerIndex=$currentPlayerIndex)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(currentPlayerIndex)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rounds> {
        override fun createFromParcel(parcel: Parcel): Rounds {
            return Rounds(parcel)
        }

        override fun newArray(size: Int): Array<Rounds?> {
            return arrayOfNulls(size)
        }
    }


}