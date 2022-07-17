package com.esgi.scoremanager.models.iterator.rounds

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.esgi.scoremanager.models.iterator.Iterator

class RoundsIterator(private val maxRounds: Int):
    Iterator, Parcelable  {
    private val rounds: MutableList<Rounds?> = MutableList(maxRounds) { null }
    var index: Int = 0

    constructor(parcel: Parcel) : this(parcel.readInt()) {
        index = parcel.readInt()
    }

    override fun toList(): List<Rounds?> {
        return this.rounds.toList()
    }

    override fun isLast(): Boolean {
        Log.d("TAG", "isLast: index => $index | max => $maxRounds == ${index == maxRounds - 1}")
        return index == maxRounds - 1
    }

    override fun getCurrent(): Rounds? {
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

    override fun addItem(item: Rounds) {
        val rounds: Rounds = item
        if (this.rounds[0] == null){
            this.rounds[0] = rounds
            return
        }
        index += 1
        this.rounds[index] = rounds

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