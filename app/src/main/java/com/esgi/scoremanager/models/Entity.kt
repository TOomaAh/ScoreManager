package com.esgi.scoremanager.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

abstract class Entity(private var name: String) : Parcelable {
    abstract fun getName() : String?
    abstract fun setName(name: String)
    override fun toString(): String {
        return "Entity(name='$name')"
    }


}