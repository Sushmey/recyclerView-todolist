package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter//Globally iniliatizing todoAdapter.
    //lateinit means we are promising to Kotlin that we will initialize it later
    override fun onCreate(savedInstanceState: Bundle?) { //This is a lifecycle function which kind of is like main function where it runs the code inside here everytime the app is created.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //This function accepts the xml file which basically configures the layout of the app, loosely similar to the html/css in webdev

        todoAdapter = TodoAdapter(mutableListOf()) //Initializing todoAdapter with an initial value of an empty mutable list, you can add hardcoded values also if you want

        rvTodoItems.adapter = todoAdapter //Attaching the rv adapter to the adapter file
        rvTodoItems.layoutManager = LinearLayoutManager(this)//Layout manager for the rv, you can use any other layout like grid also

        //Button to add todos
        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString() //getting the text from the edit text widget, converting it since originally it is of type Editable.
            if(todoTitle.isNotEmpty())
            {
                val todo = Todo(todoTitle,false) //creating an instance of a class by passing the values fetched
                todoAdapter.addTodo(todo) //Calling the add function through the adapter and sending the instance of the class
                etTodoTitle.text.clear() //clearing the text box for the next input

            }
        }

        //Button to delete todos
        btnDeleteTodo.setOnClickListener{
            todoAdapter.deleteDoneTodos()
        }



    }
}