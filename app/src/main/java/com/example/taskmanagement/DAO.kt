package com.example.taskmanagement
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface DAO {
    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTask(taskId: Long)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTask(): List<Task>
}