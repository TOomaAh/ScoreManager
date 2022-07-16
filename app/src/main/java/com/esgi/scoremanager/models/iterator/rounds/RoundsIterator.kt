package com.esgi.scoremanager.models.iterator.rounds

import android.os.Parcel
import android.os.Parcelable
import com.esgi.scoremanager.models.iterator.Iterator
import com.esgi.scoremanager.models.iterator.IteratorITem
import kotlinx.android.parcel.Parcelize

class RoundsIterator(val maxRounds: Int):
    Iterator, Parcelable  {
    private val rounds: MutableList<Rounds?> = MutableList(maxRounds) { null }
    var index: Int = 0

    constructor(parcel: Parcel) : this(parcel.readInt()) {
        index = parcel.readInt()
    }

    override fun get(index: Int): IteratorITem? {
        return this.rounds[index]
    }


    override fun hasNext(): Boolean {
        if (index >= maxRounds) {
            return false
        }
        return rounds[index + 1] != null
    }

    override fun getNext(): Rounds? {
        if (index >= maxRounds){
            return null
        }
        index += 1
        return rounds[index]
    }

    override fun reset() {
        this.index = 0
    }

    override fun addItem(item: IteratorITem) {
        val rounds: Rounds = item as Rounds
        rounds.players.map {
            it.resetThrow()
        }
        this.rounds[index] = rounds
        index += 1
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //super.writeToParcel(parcel, flags)
        parcel.writeInt(maxRounds)
        parcel.writeInt(index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RoundsIterator> {
        override fun createFromParcel(parcel: Parcel): RoundsIterator {
            return RoundsIterator(parcel)
        }

        override fun newArray(size: Int): Array<RoundsIterator?> {
            return arrayOfNulls(size)
        }
    }

}