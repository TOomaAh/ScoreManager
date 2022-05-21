package com.esgi.scoremanager.models.move

import com.esgi.scoremanager.models.Move

class Spare : Move {
    constructor() : super("Spare", 10)

    override fun canRepeat(): Boolean = true


}