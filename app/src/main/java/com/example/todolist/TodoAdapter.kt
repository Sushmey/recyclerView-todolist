package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private var todos: MutableList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo)
    {
        todos.add(todo)
        //Since the recyclerview doesn't know that we have added it to the list, we need to notify it
        notifyItemInserted(todos.size-1) //position inserted at would be the last index

    }
    fun deleteDoneTodos()
    {
        todos.removeAll { todo->todo.isChecked }
        //Since the recyclerview doesn't know that we have added it to the list, we need to notify it
        notifyDataSetChanged()
    }
    //Custom function to strikethrough when done
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean)
    {
        if(isChecked)
        {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
            //This is binary or which is equivalent to addition, it basically adds to the already existing paint flags
        }
        else
        {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            //This is binary and which is equivalent to subtraction, it basically subtracts the paint flag from it
        }
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        //Updating the views based on the content
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle,cbDone.isChecked) //Calling this function here because if it was checked initially then we want to toggle the strikethrough line
            cbDone.setOnCheckedChangeListener { _, isChecked -> //Calling it here so that when the checkbox is clicked, the function is triggered
                toggleStrikeThrough(tvTodoTitle,isChecked)
                curTodo.isChecked = !curTodo.isChecked //flipping the boolean if it is toggled
            }


        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }


}