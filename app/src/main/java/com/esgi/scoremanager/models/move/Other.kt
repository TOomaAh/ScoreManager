package com.esgi.scoremanager.models.move

import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize

@Parcelize
class Other : Move("Other", 10) {
    override fun canRepeat(): Boolean = false

}