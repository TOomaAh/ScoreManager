package com.esgi.scoremanager.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.TypeConverter
import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.move.Other
import com.esgi.scoremanager.models.move.Spare
import com.esgi.scoremanager.models.move.Strike
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject


abstract class Move(
    @ColumnInfo val name: String,
    @ColumnInfo val points: Int) : Parcelable {
    abstract fun canRepeat(): Boolean
    override fun toString(): String {
        return "Move(name='$name', points=$points)"
    }
}

class MoveConverter {
    @TypeConverter
    fun ConvertMove(move: Move?) : String? {
        return JSONObject().apply {
            put("name", move?.name)
            put("point", move?.points)
        }.toString()
    }

    @TypeConverter
    fun ConvertMove(move: String?) : Move? {
        val json = JSONObject(move)
        return when (json.get("name")) {
            "Strike" -> Strike()
            "Spare" -> Spare()
            "Other" -> Other(json.getInt("points"))
            else -> null
        }
    }
}