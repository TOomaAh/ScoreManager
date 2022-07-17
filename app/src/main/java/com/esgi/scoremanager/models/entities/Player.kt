package com.esgi.scoremanager.models.entities

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize
import kotlin.jvm.Throws


class Player(
    @ColumnInfo var name: String,
    @ColumnInfo var moves: MutableList<Move> = mutableListOf(),
    private val maxMove: Int) : Parcelable {


    private var currentThrows: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        mutableListOf<Move>(),
        parcel.readInt()
    ) {
        currentThrows = parcel.readInt()
    }


    fun resetAll() {
        currentThrows = 0
        moves.removeAll { true }
    }


    fun formatCurrentThrowsIndex() : String {
        return "${currentThrows + 1} throw"
    }

    fun getCurrentThrowsIndex() : Int {
        return currentThrows + 1
    }

    fun addMove(move: Move) : Boolean {
        if (moves.size == maxMove)
            return false

        if (moves.size > 0) {
            if (!moves[0].canRepeat() || !move.canRepeat()) {
                return false
            }
        }

        moves.add(move)
        return true
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeList(moves)
        parcel.writeInt(maxMove)
        parcel.writeInt(currentThrows)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Player(name='$name', moves=$moves, maxMove=$maxMove, currentThrows=$currentThrows)"
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