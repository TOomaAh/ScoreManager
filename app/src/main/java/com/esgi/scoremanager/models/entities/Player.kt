package com.esgi.scoremanager.models.entities

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.TypeConverter
import com.esgi.scoremanager.models.Move
import com.esgi.scoremanager.models.MoveConverter
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import com.esgi.scoremanager.models.move.Spare
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONObject
import kotlin.jvm.Throws


class Player(
    @ColumnInfo var name: String,
    @ColumnInfo var moves: MutableList<Move> = mutableListOf(),
    val maxMove: Int) : Parcelable {


    private var currentThrows: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        mutableListOf<Move>(),
        parcel.readInt()
    ) {
        currentThrows = parcel.readInt()
    }


    fun resetAll() {
        currentThrows = 0
        moves.removeAll { true }
    }


    fun formatCurrentThrowsIndex() : String {
        return "${currentThrows + 1} throw"
    }

    fun getCurrentThrowsIndex() : Int {
        return currentThrows + 1
    }

    fun addMove(move: Move) : Boolean {
        if (moves.size == maxMove)
            return false

        if (moves.size > 0) {

            if ((moves[0].points < 10 && move is Spare) || ((moves[0].points + move.points) == 10)) {
                moves[0] = Spare()
                currentThrows = this.maxMove
                return true
            }

            if (!moves[0].canRepeat() || !move.canRepeat()) {
                return false
            }

            if (moves[0].points + move.points > 10) {
                return false
            }

        }

        moves.add(move)
        currentThrows += if (move.canRepeat()) {
            1
        } else {
            this.maxMove
        }

        return true
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeList(moves)
        parcel.writeInt(maxMove)
        parcel.writeInt(currentThrows)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Player(name='$name', moves=$moves, maxMove=$maxMove, currentThrows=$currentThrows)"
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}

class PlayerConverter {
    @TypeConverter
    fun ConvertPlayer(player: Player?) : String? {
        return JSONObject().apply {
            val moveConverter = MoveConverter()
            val moves : MutableList<String> = mutableListOf()
            put("name", player?.name)
            player?.moves?.map {
                moves.add(moveConverter.ConvertMove(it)!!)
            }
            put("moves", moves)
            put("maxMove", player?.maxMove)
        }.toString()
    }

    @TypeConverter
    fun ConvertPlayer(player: String?) : Player {
        val json = JSONObject(player)
        val moveConverter: MoveConverter = MoveConverter()
        val movesJson = JSONArray(json.getString("moves"))
        val moves: MutableList<Move> = mutableListOf()
        for (i in 0 until movesJson.length()) {
            val jsonObject = movesJson.getJSONObject(i)
            moves.add(moveConverter.ConvertMove(jsonObject.toString())!!)
        }
        return Player(json.getString("name"), moves, json.getInt("maxMove"))
    }
}

class PlayerListConverter {

    @TypeConverter
    fun ConvertPlayerList(players: List<Player?>) : String? {
        return JSONArray().apply {
            players.map {
                val jsonObject = JSONObject()
                val moveConverter = MoveConverter()
                val moves : MutableList<String> = mutableListOf()
                jsonObject.put("name", it?.name)
                it?.moves?.map {
                    moves.add(moveConverter.ConvertMove(it)!!)
                }
                jsonObject.put("moves", moves)
                jsonObject.put("maxMove", it?.maxMove)
                this.put(jsonObject)
            }
        }.toString()
    }

    @TypeConverter
    fun ConvertPlayerList(player: String?) : List<Player> {
        val players: MutableList<Player> = mutableListOf()
        val moveConverter: MoveConverter = MoveConverter()
        val json = JSONArray(player)
        //Pour chaque player
        for (r in 0 until json.length()) {
            val playerJson = json.getJSONObject(r)
            val movesJson = JSONArray(playerJson.getString("moves"))
            val moves: MutableList<Move> = mutableListOf()
            for (i in 0 until movesJson.length()) {
                val jsonObject = movesJson.getJSONObject(i)
                moves.add(moveConverter.ConvertMove(jsonObject.toString())!!)
            }
            players.add(Player(playerJson.getString("name"), moves, playerJson.getInt("maxMove")))

        }
        return players
    }
}