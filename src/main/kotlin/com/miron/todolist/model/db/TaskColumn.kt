package com.miron.todolist.model.db

enum class TaskColumn(val columnName: String) {
    ID("Task Id"),
    NAME("Task Name"),
    DESCRIPTION("Task Description"),
    CREATED_AT("Created at");
}