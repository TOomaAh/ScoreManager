package com.esgi.scoremanager.models

import android.os.Parcelable

abstract class Move(private val name: String, private val points: Int) : Parcelable {

    abstract fun canRepeat(): Boolean
}