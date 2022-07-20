package com.esgi.scoremanager.models.iterator.rounds

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverter
import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.entities.PlayerConverter
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONObject


@Entity
open class Rounds(
    var players : MutableList<Player> = mutableListOf(),
    var currentPlayerIndex: Int = 0
) : Parcelable {



    constructor(parcel: Parcel) : this() {
        currentPlayerIndex = parcel.readInt()
    }

    constructor(p: MutableList<Player>) : this() {
        val newPlayers = mutableListOf<Player>()
        for (i in p) {
            newPlayers.add(Player(i.name, mutableListOf(), 2))
        }
        this.players = newPlayers
    }



    override fun toString(): String {
        return "Rounds(players=$players, currentPlayerIndex=$currentPlayerIndex)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(currentPlayerIndex)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rounds> {
        override fun createFromParcel(parcel: Parcel): Rounds {
            return Rounds(parcel)
        }

        override fun newArray(size: Int): Array<Rounds?> {
            return arrayOfNulls(size)
        }
    }
}

class RoundConverter {

    @TypeConverter
    fun ConvertRound(round: Rounds?) : String? {
        return JSONObject().apply {
            var players : MutableList<String> = mutableListOf()
            var playerConverter = PlayerConverter()
            round?.players?.map {
                players.add(playerConverter.ConvertPlayer(it)!!)
            }
            put("players", players)
        }.toString()
    }

    @TypeConverter
    fun ConvertRound(round: String?) : Rounds? {
        val json = JSONObject(round)
        val playersJson = JSONArray(json.getString("players"))
        val players: MutableList<Player> = mutableListOf()
        val playerConverter = PlayerConverter()
        for (i in 0 until playersJson.length()) {
            val jsonObject = playersJson.getJSONObject(i)
            players.add(playerConverter.ConvertPlayer(jsonObject.toString())!!)
        }
        return Rounds(players, 0)
    }
}

class RoundListConverter {

    @TypeConverter
    fun ConvertRoundList(rounds: List<Rounds?>) : String? {
        return JSONArray().apply {
            rounds.map {
                val jsonObject = JSONObject()
                var players : MutableList<String> = mutableListOf()
                var playerConverter = PlayerConverter()
                it?.players?.map {
                    players.add(playerConverter.ConvertPlayer(it)!!)
                }
                jsonObject.put("players", players)
                this.put(jsonObject)
            }
        }.toString()
    }

    @TypeConverter
    fun ConvertRoundList(round: String?) : List<Rounds?> {
        val rounds: MutableList<Rounds> = mutableListOf()

        val json = JSONArray(round)
        val roundConverters = RoundConverter()

        for (i in 0 until json.length()) {
            val round = roundConverters.ConvertRound(json.getString(i))
            if (round != null) {
                rounds.add(Rounds(round.players, round.currentPlayerIndex))
            }
        }


        /*for (r in 0 until json.length()) {
            val roundJson = json.getJSONArray(r)
            val playersJson = roundJson.getJSONArray(r)
            val players: MutableList<Player> = mutableListOf()
            val playerConverter = PlayerConverter()
            for (i in 0 until playersJson.length()) {
                val jsonObject = playersJson.getJSONObject(i)
                players.add(playerConverter.ConvertPlayer(jsonObject.toString())!!)
            }
            rounds.add(Rounds(players, 0))
        }*/
        return rounds
    }
}