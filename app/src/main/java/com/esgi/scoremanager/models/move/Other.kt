package com.esgi.scoremanager.models.move

import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize

@Parcelize
class Other(var p: Int) : Move("Other", p) {
    override fun canRepeat(): Boolean = true

}