package com.miron.todolist.mapper

import com.miron.todolist.model.Task
import com.miron.todolist.model.db.TaskColumn
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TaskMapper {

    fun map(item: Map<String, AttributeValue>): Task {
        val id = UUID.fromString(item[TaskColumn.ID.columnName]!!.s())
        val name = item[TaskColumn.NAME.columnName]!!.s()
        val description = item[TaskColumn.DESCRIPTION.columnName]?.s()

        return Task(id, name, description)
    }
}