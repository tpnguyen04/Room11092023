package com.example.room11092023.data.database.Entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(true)
    val id: Int,
    @ColumnInfo("id_status")
    val idStatus: Int,
    val name: String,
    @ColumnInfo("start_date")
    val startDate: Long,
    @ColumnInfo("end_date")
    val endDate: Long
)