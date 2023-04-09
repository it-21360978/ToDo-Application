package com.example.todo.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class ToDo(var item: String?
        ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}