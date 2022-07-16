package com.esgi.scoremanager.models.sport

import android.os.Parcelable
import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.rounds.Rounds
import kotlinx.android.parcel.Parcelize

@Parcelize
class SportBuilder(
    var entities: MutableList<Entity> = mutableListOf(),
            val rounds: MutableList<Rounds?> = mutableListOf()
    ) : Builder {



    private var name: String = "Sport"

    override fun setName(name: String) {
        this.name = name
    }

    override fun addEntity(entity: Entity) {
        this.entities.add(entity)
    }

    override fun removeEntity(entity: Entity) {
        this.entities.remove(entity)
    }

    override fun addRound(rounds: Rounds) {
        this.rounds.add(rounds)
    }

    override fun build() : Sport {
        return Bowling(name, entities, rounds)
    }

}