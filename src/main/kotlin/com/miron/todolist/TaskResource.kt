package com.miron.todolist

import com.miron.todolist.extension.logger
import com.miron.todolist.model.CreateTaskRequest
import com.miron.todolist.model.Task
import com.miron.todolist.service.TaskService
import java.util.*
import javax.inject.Inject
import javax.ws.rs.*

@Path("tasks")
@Produces("application/json")
@Consumes("application/json")
class TaskResource @Inject constructor(
        private val taskService: TaskService
) {

    @GET
    @Path("/{id}")
    fun get(@PathParam("id") id: UUID): Task? {
        log.info("Fetching task by id {}", id)
        return taskService.get(id)
    }

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: UUID) {
        log.info("Deleting task with id {}", id)
        taskService.delete(id)
    }

    @PUT
    fun create(task: CreateTaskRequest): UUID {
        log.info("Creating the new task {}", task)
        val uuid = taskService.create(task)
        log.info("Created {}", uuid)
        return uuid
    }

    companion object {
        private val log = logger<TaskResource>()
    }
}