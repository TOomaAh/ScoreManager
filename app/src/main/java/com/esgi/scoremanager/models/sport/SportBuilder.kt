package com.esgi.scoremanager.models.sport

import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.iterator.rounds.RoundsIterator
import kotlinx.android.parcel.Parcelize

@Parcelize
class SportBuilder(
    var entities: MutableList<Player> = mutableListOf(),
    val maxRounds : Int
    ) : Builder {

    private val rounds: RoundsIterator = RoundsIterator(maxRounds)

    private var name: String = "Sport"

    override fun setName(name: String) {
        this.name = name
    }

    override fun addEntity(entity: Player) {
        this.entities.add(entity)
    }

    override fun removeEntity(entity: Player) {
        this.entities.remove(entity)
    }

    override fun build() : Sport {
        return Bowling(name, entities, rounds)
    }

}