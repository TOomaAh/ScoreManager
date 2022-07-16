package com.esgi.scoremanager.models.iterator

import android.os.Parcel
import android.os.Parcelable
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import kotlinx.android.parcel.Parcelize


interface Iterator : Parcelable{
    fun hasNext() : Boolean
    fun getNext() : IteratorITem?
    fun addItem(item: IteratorITem)
    fun get(index: Int) : IteratorITem?
    fun reset()
    override fun writeToParcel(parcel: Parcel, flags: Int)
}