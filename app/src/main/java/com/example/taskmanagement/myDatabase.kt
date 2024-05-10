package com.example.taskmanagement

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanagement.Entity

@Database(entities = [Entity::class],version=1)
abstract class myDatabase : RoomDatabase() {
    abstract fun dao():DAO
}