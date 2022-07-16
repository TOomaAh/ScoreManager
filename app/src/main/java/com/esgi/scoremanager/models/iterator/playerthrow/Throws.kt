package com.esgi.scoremanager.models.iterator.playerthrow

import android.os.Parcel
import android.os.Parcelable
import com.esgi.scoremanager.models.Move
import com.esgi.scoremanager.models.iterator.IteratorITem
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import kotlinx.android.parcel.Parcelize

class Throws(val move: Move?) : IteratorITem(), Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable(Move::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(move, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Throws> {
        override fun createFromParcel(parcel: Parcel): Throws {
            return Throws(parcel)
        }

        override fun newArray(size: Int): Array<Throws?> {
            return arrayOfNulls(size)
        }
    }
}