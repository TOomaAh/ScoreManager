package com.esgi.scoremanager.models.move

import com.esgi.scoremanager.models.Move
import kotlinx.android.parcel.Parcelize

@Parcelize
class Other(var points: Int) : Move("Other", points) {
    override fun canRepeat(): Boolean = true

}