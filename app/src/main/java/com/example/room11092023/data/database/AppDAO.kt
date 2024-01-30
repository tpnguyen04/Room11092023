package com.example.room11092023.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.room11092023.data.database.Entity.Status
import com.example.room11092023.data.database.Entity.Task

@Dao
interface AppDAO {
    @Query("SELECT * FROM task")
    fun getListTask(): List<Task>

    @Query("SELECT * FROM status")
    fun getListStatus(): List<Status>

    @Insert
    fun addTask(task: Task): Long

    @Insert
    fun addStatus(status: List<Status>): List<Long>
}