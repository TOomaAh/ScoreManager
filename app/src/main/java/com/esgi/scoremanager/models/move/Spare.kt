package com.esgi.scoremanager.models.move

import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize

@Parcelize
class Spare : Move("Spare", 10) {
    override fun canRepeat(): Boolean = true
}