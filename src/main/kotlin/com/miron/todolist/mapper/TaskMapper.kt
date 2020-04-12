package com.miron.todolist.mapper

import com.miron.todolist.model.Task
import com.miron.todolist.model.db.TaskColumn
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.time.LocalDateTime
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TaskMapper {

    fun map(item: Map<String, AttributeValue>): Task {
        return Task(
            id = UUID.fromString(item[TaskColumn.ID.columnName]!!.s()),
            name = item[TaskColumn.NAME.columnName]!!.s(),
            description = item[TaskColumn.DESCRIPTION.columnName]?.s(),
            createdAt = LocalDateTime.parse(item[TaskColumn.CREATED_AT.columnName]!!.s())
        )
    }
}