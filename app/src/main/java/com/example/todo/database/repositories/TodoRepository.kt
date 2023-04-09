package com.example.todo.database.repositories

import com.example.todo.database.ToDoDatabase
import com.example.todo.database.entities.ToDo

class TodoRepository(private val db: ToDoDatabase) {
    suspend fun insert(todo:ToDo) = db.getTodoDao().insertTodo(todo)
    suspend fun delete(todo: ToDo) = db.getTodoDao().delete(todo)
    fun getAllTodos() =db.getTodoDao().getAlltodo()
}