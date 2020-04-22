package com.miron.todolist

import com.miron.todolist.extension.logger
import com.miron.todolist.model.CreateTaskRequest
import com.miron.todolist.model.Task
import com.miron.todolist.service.TaskService
import java.util.*
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.HttpHeaders.AUTHORIZATION

@Path("tasks")
@Produces("application/json")
@Consumes("application/json")
class TaskResource @Inject constructor(
        private val taskService: TaskService
) {

    @GET
    @Path("/{id}")
    fun get(@PathParam("id") id: UUID, @HeaderParam(AUTHORIZATION) userId: UUID): Task? {
        log.info("Fetching task by id {}", id)
        return taskService.get(userId, id)
    }

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: UUID, @HeaderParam(AUTHORIZATION) userId: UUID) {
        log.info("Deleting task with id {}", id)
        taskService.delete(userId, id)
    }

    @PUT
    fun create(task: CreateTaskRequest, @HeaderParam(AUTHORIZATION) userId: UUID): UUID {
        log.info("Creating the new task {}", task)
        val uuid = taskService.create(userId, task)
        log.info("Created {}", uuid)
        return uuid
    }

    @GET
    fun getAll(@HeaderParam(AUTHORIZATION) userId: UUID): List<Task> {
        return taskService.getAll(userId)
    }

    companion object {
        private val log = logger<TaskResource>()
    }
}