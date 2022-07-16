package com.esgi.scoremanager.models.rounds

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RoundsIterator(private val mutableList: MutableList<Rounds?> = mutableListOf(), val maxRounds: Int) : Iterator {

    var index: Int = 0


    override fun hasNext(): Boolean {
        if (index >= maxRounds) {
            return false
        }
        return mutableList[index + 1] != null
    }

    override fun getNext(): Rounds? {
        if (index >= maxRounds){
            return null
        }
        index += 1
        return mutableList[index]
    }

    override fun reset() {
        this.index = 0
    }
}