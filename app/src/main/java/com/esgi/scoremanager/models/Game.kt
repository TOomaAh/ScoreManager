package com.esgi.scoremanager.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import com.esgi.scoremanager.models.sport.Sport
import java.util.*

@Entity
class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val rounds: List<Rounds?>,
    @ColumnInfo val players: List<Player>) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createTypedArrayList(Rounds)!!,
        parcel.createTypedArrayList(Player)!!
    ) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeTypedList(rounds)
        parcel.writeTypedList(players)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Game(id=$id, name='$name', rounds=$rounds, players=$players)"
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}