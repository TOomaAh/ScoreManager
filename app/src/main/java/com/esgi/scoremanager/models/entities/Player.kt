package com.esgi.scoremanager.models.entities

import android.os.Parcelable
import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.Move
import com.esgi.scoremanager.models.rounds.RoundsIterator
import com.esgi.scoremanager.models.rounds.Throws
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player(var name: String, var throws: RoundsIterator = RoundsIterator(mutableListOf(), 2)) : Entity(name), Parcelable {

}