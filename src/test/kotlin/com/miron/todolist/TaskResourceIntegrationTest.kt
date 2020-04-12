package com.miron.todolist

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.miron.todolist.extension.logger
import com.miron.todolist.model.CreateTaskRequest
import com.miron.todolist.model.Task
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import java.util.*
import org.hamcrest.Matchers.`is` as Is

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class TaskResourceIntegrationTest {

    private val objectMapper = jacksonObjectMapper()

    @Test
    @Order(1)
    fun shouldCreateNewTask() {
        val task = CreateTaskRequest(name = "test task", description = "test description")
        val response = given()
                .contentType("application/json")
                .body(objectMapper.writeValueAsString(task))
                .`when`().put("/tasks")
                .thenReturn()

        assertThat(response.statusCode, Is(200))

        val body = response.body.asString()
        assertThat(body, notNullValue())

        uuid = objectMapper.readValue<UUID>(body)

//        log.info("Created new task with uuid {}", uuid)
    }

    @Test
    @Order(2)
    fun shouldReturnTaskById() {
        val response = given()
                .contentType("application/json")
                .`when`().get("/tasks/" + uuid.toString())
                .thenReturn()

        assertThat(response.statusCode, Is(200))

        val body = response.body.asString()
        val actualTask = objectMapper.readValue<Task>(body)

        assertThat(actualTask.name, Is("test task"))
        assertThat(actualTask.description, Is("test description"))
    }

    @Test
    @Order(3)
    fun shouldDeleteTaskById() {
        val response = given()
                .contentType("application/json")
                .`when`().delete("/tasks/" + uuid.toString())
                .thenReturn()

        assertThat(response.statusCode, Is(200))
    }

    companion object {
        private var uuid: UUID? = null
    }

}