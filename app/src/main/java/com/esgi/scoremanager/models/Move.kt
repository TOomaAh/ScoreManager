package com.esgi.scoremanager.models

abstract class Move(private val name: String, private val points: Int) {

    abstract fun canRepeat(): Boolean
}