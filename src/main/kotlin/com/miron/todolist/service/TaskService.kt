package com.miron.todolist.service

import com.miron.todolist.mapper.TaskMapper
import com.miron.todolist.model.CreateTaskRequest
import com.miron.todolist.model.Task
import com.miron.todolist.model.db.TaskColumn
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import java.time.LocalDateTime
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class TaskService @Inject constructor(private val dynamoClient: DynamoDbClient, private val taskMapper: TaskMapper) {

    fun getAll(): List<Task> {
        return arrayListOf()
    }

    fun get(id: UUID): Task? {
        val item = mapOf<String, AttributeValue>(
                TaskColumn.ID.columnName to AttributeValue.builder().s(id.toString()).build()
        )

        val request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(item)
                .build()

        val response = dynamoClient.getItem(request)
        return taskMapper.map(response.item())
    }

    fun delete(id: UUID) {
        val item = mapOf<String, AttributeValue>(
                TaskColumn.ID.columnName to AttributeValue.builder().s(id.toString()).build()
        )

        val request = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(item)
                .build()

        dynamoClient.deleteItem(request)
    }

    fun create(task: CreateTaskRequest): UUID {
        val taskId = UUID.randomUUID()

        val item = mapOf<String, AttributeValue>(
                TaskColumn.ID.columnName to AttributeValue.builder().s(taskId.toString()).build(),
                TaskColumn.NAME.columnName to AttributeValue.builder().s(task.name).build(),
                TaskColumn.DESCRIPTION.columnName to AttributeValue.builder().s(task.description).build(),
                TaskColumn.CREATED_AT.columnName to AttributeValue.builder().s(LocalDateTime.now().toString()).build()
        )

        val request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build()

        dynamoClient.putItem(request)

        return taskId
    }

    companion object {
        private const val TABLE_NAME = "todolist"
    }
}