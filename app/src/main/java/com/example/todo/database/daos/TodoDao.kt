package com.example.todo.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.database.entities.ToDo

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodo(todo: ToDo)
    @Delete
    suspend fun delete(todo:ToDo)
    @Query("SELECT * From Todo")
    fun getAlltodo(): List<ToDo>
}