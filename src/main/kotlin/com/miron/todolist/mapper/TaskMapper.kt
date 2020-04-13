package com.miron.todolist.mapper

import com.miron.todolist.model.Task
import com.miron.todolist.model.db.TodolistTable
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.time.LocalDateTime
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TaskMapper {

    fun map(items: List<Map<String, AttributeValue>>): List<Task> {
        return items.map { map(it) }
    }

    fun map(item: Map<String, AttributeValue>): Task {
        val id = item[TodolistTable.TASK_ID.columnName]
        val name = item[TodolistTable.TASK_NAME.columnName]
        val description = item[TodolistTable.TASK_DESCRIPTION.columnName]
        val createdAt = item[TodolistTable.TASK_CREATED_AT.columnName]
        return Task(
                id = UUID.fromString(id!!.s()),
                name = name!!.s(),
                description = description?.s(),
                createdAt = LocalDateTime.parse(createdAt!!.s())
        )
    }
}