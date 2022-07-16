package com.esgi.scoremanager.models.iterator.playerthrow

import android.os.Parcel
import android.os.Parcelable
import com.esgi.scoremanager.models.iterator.Iterator
import com.esgi.scoremanager.models.iterator.IteratorITem
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import kotlinx.android.parcel.Parcelize

class ThrowIterator(private val nbrThrows: Int = 2): Iterator , Parcelable {

    private var throws: MutableList<Throws?> = MutableList(nbrThrows) { Throws(null) }
    private var index : Int = 0

    constructor(parcel: Parcel) : this(parcel.readInt()) {
        index = parcel.readInt()
    }

    override fun get(index: Int): IteratorITem? {
        return this.throws[index]
    }


    override fun hasNext(): Boolean {
        if (index >= 2 ||
            (this.throws[index] != null && this.throws[index]?.move != null && this.throws[index]?.move?.canRepeat() == false)
        ) {
            return false
        }
        return this.throws[index + 1] != null
    }

    override fun getNext(): Throws? {
        if (!this.hasNext()){
            return null
        }
        this.index += 1
        return this.throws[index]
    }

    override fun addItem(item: IteratorITem) {
        this.throws[index] = item as Throws
        index += 1
    }

    override fun reset() {
        this.index = 0
    }


    fun getCurrentThrowsIndex() : Int {
        return this.index
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //super<Iterator>.writeToParcel(parcel, flags)
        parcel.writeInt(nbrThrows)
        parcel.writeInt(index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ThrowIterator> {
        override fun createFromParcel(parcel: Parcel): ThrowIterator {
            return ThrowIterator(parcel)
        }

        override fun newArray(size: Int): Array<ThrowIterator?> {
            return arrayOfNulls(size)
        }
    }
}