package com.esgi.scoremanager.models

import android.os.Parcelable
import androidx.room.ColumnInfo

abstract class Move(
    @ColumnInfo private val name: String,
    @ColumnInfo private val points: Int) : Parcelable {
    abstract fun canRepeat(): Boolean
}