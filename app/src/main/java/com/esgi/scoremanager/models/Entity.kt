package com.esgi.scoremanager.models

abstract class Entity(private var name: String) {
    abstract fun getName() : String
    abstract fun setName(name: String)
}