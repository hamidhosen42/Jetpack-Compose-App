package com.hamidhosen.todoapp

import java.util.Date

object TodoManager {
    private var todoList = mutableListOf<Todo>()

    fun getAllTodo(): List<Todo> {
        return todoList
    }

    fun addTodo(title: String) {
        todoList.add(
            Todo(System.currentTimeMillis().toInt(), title, Date())
        )
    }

    fun deleteTodo(id: Int) {
        todoList.removeIf {
            it.id == id
        }
    }
}
