package com.example.room11092023.data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "status")
data class Status(
    @PrimaryKey(true)
    val id: Int,
    @TypeConverters(StatusConverter::class)
    val name: StatusEnum
)

enum class StatusEnum {
    INVESTIGATE,
    PROGRESS,
    DONE
}

class StatusConverter {

    @TypeConverter
    fun toStatus(value: Int) = enumValues<StatusEnum>()[value]

    @TypeConverter
    fun fromStatus(value: StatusEnum) = value.ordinal
}