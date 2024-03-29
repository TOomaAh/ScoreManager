package com.esgi.scoremanager.models.move

import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize

@Parcelize
class Strike : Move("Strike", 20) {
    override fun canRepeat(): Boolean = false
}