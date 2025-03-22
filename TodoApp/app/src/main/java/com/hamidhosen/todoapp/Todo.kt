package com.hamidhosen.todoapp

import java.time.Instant
import java.util.Date

data class Todo(
    var id: Int,
    var title: String,
    var createAt: Date
)

fun getFakeTodo(): List<Todo> {
    return listOf<Todo>(
        Todo(1, "First Todo", Date.from(Instant.now())),
        Todo(2, "First Todo1", Date.from(Instant.now())),
        Todo(2, "First Todo1", Date.from(Instant.now())),
    );
}
