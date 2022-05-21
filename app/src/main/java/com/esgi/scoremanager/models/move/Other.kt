package com.esgi.scoremanager.models.move

import com.esgi.scoremanager.models.Move

class Other : Move {

    constructor() : super("Other", 10)

    override fun canRepeat(): Boolean = false

}