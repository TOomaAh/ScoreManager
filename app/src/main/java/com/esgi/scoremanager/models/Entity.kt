package com.esgi.scoremanager.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
abstract class Entity(var nameEntity: String) : Parcelable {
    override fun toString(): String {
        return "Entity(name='$nameEntity')"
    }


}