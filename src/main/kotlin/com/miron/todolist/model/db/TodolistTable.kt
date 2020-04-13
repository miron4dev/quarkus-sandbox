package com.miron.todolist.model.db

enum class TodolistTable(val columnName: String) {
    USER_ID("UserId"),
    TASK_ID("TaskId"),
    TASK_NAME("TaskName"),
    TASK_DESCRIPTION("TaskDescription"),
    TASK_CREATED_AT("CreatedAt");
}