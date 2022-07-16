package com.esgi.scoremanager.models.entities

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.esgi.scoremanager.models.Move
import com.esgi.scoremanager.models.iterator.playerthrow.ThrowIterator
import kotlinx.android.parcel.Parcelize
import kotlin.jvm.Throws


class Player(var name: String, val nbrRounds: Int) : Parcelable {

    private val throws: MutableList<ThrowIterator> = MutableList(nbrRounds) { ThrowIterator() }

    private var currentThrows: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }


    fun getThrows() : MutableList<ThrowIterator> {
        return this.throws
    }

    fun formatCurrentThrowsIndex() : String {
        return "${currentThrows + 1} throw"
    }

    fun getCurrentThrowsIndex() : Int {
        return currentThrows + 1
    }

    fun resetThrow(){
        currentThrows = 0
    }

    fun addMove(move: Move) {
        throws[currentThrows].addItem(com.esgi.scoremanager.models.iterator.playerthrow.Throws(move))
        if (!move.canRepeat()){
            currentThrows = 2
            return
        }
        currentThrows += 1

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(nbrRounds)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Player(name='$name', nbrRounds=$nbrRounds, throws=$throws, currentThrows=$currentThrows)"
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }



}