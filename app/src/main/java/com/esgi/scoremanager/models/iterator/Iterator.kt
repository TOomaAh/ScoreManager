package com.esgi.scoremanager.models.iterator

import android.os.Parcel
import android.os.Parcelable
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import kotlinx.android.parcel.Parcelize


interface Iterator : Parcelable{
    fun hasNext() : Boolean
    fun getNext() : Rounds?
    fun addItem(item: Rounds)
    fun getCurrent() : Rounds?
    fun isLast() : Boolean
    fun toList() : List<Rounds?>
    fun reset()
    override fun writeToParcel(parcel: Parcel, flags: Int)
}