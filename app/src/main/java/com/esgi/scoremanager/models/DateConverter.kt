package com.esgi.scoremanager.models

import androidx.room.TypeConverter
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class DateConverter {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")

    @TypeConverter
    fun DateConvert(date: String?) : LocalDateTime {
        return LocalDateTime.parse(date, formatter)
    }

    @TypeConverter
    fun DateConvert(date: LocalDateTime?) : String? {
        return date?.format(formatter)
    }
}