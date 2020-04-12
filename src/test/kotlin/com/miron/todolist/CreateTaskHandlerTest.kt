package com.miron.todolist

import com.miron.todolist.model.CreateTaskRequest
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
internal class CreateTaskHandlerTest {

    @Inject
    lateinit var handler: CreateTaskHandler

    @Test
    fun shouldCreateNewTask() {
        val request = CreateTaskRequest(name = "test task", description = "test description")
        val response = handler.handleRequest(request, null)

        assertNotNull(response)
    }
}