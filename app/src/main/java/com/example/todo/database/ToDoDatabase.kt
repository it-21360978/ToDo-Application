package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.daos.TodoDao
import com.example.todo.database.entities.ToDo

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null
        fun getInstance(context: Context): ToDoDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}