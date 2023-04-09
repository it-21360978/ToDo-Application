package com.example.todo.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.ToDoDatabase
import com.example.todo.database.entities.ToDo
import com.example.todo.database.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class TodoAdapter(): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    lateinit var context: Context
    lateinit var data: List<ToDo>


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cbTodos: CheckBox
        val ivDelete: ImageView

        init {
            cbTodos = view.findViewById(R.id.cbitem)
            ivDelete = view.findViewById(R.id.delete)
        }
    }

    fun setData(data: List<ToDo>, context: Context) {
        this.data = data
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {

        return data.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cbTodos.text = data[position].item
        holder.ivDelete.setOnClickListener {
            if(holder.cbTodos.isChecked){
                val repository = TodoRepository(ToDoDatabase.getInstance(context))

                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(data[position])
                    val data = repository.getAllTodos()
                    withContext(Dispatchers.Main) {
                        setData(data, context)

                    }
                }
            }else{
                Toast.makeText(context,"Cannot delete unmarked Todo items",Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

}