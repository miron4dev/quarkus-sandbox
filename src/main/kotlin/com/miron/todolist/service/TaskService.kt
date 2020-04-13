package com.miron.todolist.service

import com.miron.todolist.mapper.TaskMapper
import com.miron.todolist.model.CreateTaskRequest
import com.miron.todolist.model.Task
import com.miron.todolist.model.db.TodolistTable
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import software.amazon.awssdk.services.dynamodb.model.QueryRequest
import java.time.LocalDateTime
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class TaskService @Inject constructor(
        private val dynamoClient: DynamoDbClient,
        private val taskMapper: TaskMapper
) {

    fun getAll(userId: UUID): List<Task> {
        val expressionAttributes = mapOf<String, AttributeValue>(
                ":user_id" to AttributeValue.builder().s(userId.toString()).build()
        )

        val request = QueryRequest.builder()
                .tableName(TABLE_NAME)
                .keyConditionExpression("${TodolistTable.USER_ID.columnName} = :user_id")
                .expressionAttributeValues(expressionAttributes)
                .build()

        val response = dynamoClient.query(request)
        return taskMapper.map(response.items())
    }

    fun get(userId: UUID, taskId: UUID): Task? {
        val item = mapOf<String, AttributeValue>(
                TodolistTable.USER_ID.columnName to AttributeValue.builder().s(userId.toString()).build(),
                TodolistTable.TASK_ID.columnName to AttributeValue.builder().s(taskId.toString()).build()
        )

        val request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(item)
                .build()

        val response = dynamoClient.getItem(request)
        return taskMapper.map(response.item())
    }

    fun delete(userId: UUID, taskId: UUID) {
        val item = mapOf<String, AttributeValue>(
                TodolistTable.USER_ID.columnName to AttributeValue.builder().s(userId.toString()).build(),
                TodolistTable.TASK_ID.columnName to AttributeValue.builder().s(taskId.toString()).build()
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
                TodolistTable.USER_ID.columnName to AttributeValue.builder().s(task.userId.toString()).build(),
                TodolistTable.TASK_ID.columnName to AttributeValue.builder().s(taskId.toString()).build(),
                TodolistTable.TASK_NAME.columnName to AttributeValue.builder().s(task.name).build(),
                TodolistTable.TASK_DESCRIPTION.columnName to AttributeValue.builder().s(task.description).build(),
                TodolistTable.TASK_CREATED_AT.columnName to AttributeValue.builder().s(LocalDateTime.now().toString()).build()
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