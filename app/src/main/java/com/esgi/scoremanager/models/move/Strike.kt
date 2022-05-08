package com.esgi.scoremanager.models.move

import com.esgi.scoremanager.models.Move

class Strike : Move {

    constructor() : super("Strike", 10)

    override fun canContinue(): Boolean = false
}