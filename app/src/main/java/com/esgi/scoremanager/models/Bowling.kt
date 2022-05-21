package com.esgi.scoremanager.models

class Bowling : Sport {


    constructor() : super("Bowling", mutableListOf(), listOf(), 10)

    override fun getRule(): String = "Merveilleuse regle"

}