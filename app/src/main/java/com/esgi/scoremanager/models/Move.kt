package com.esgi.scoremanager.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize


abstract class Move(
    @ColumnInfo private val name: String,
    @ColumnInfo val points: Int) : Parcelable {
    abstract fun canRepeat(): Boolean
    override fun toString(): String {
        return "Move(name='$name', points=$points)"
    }


}