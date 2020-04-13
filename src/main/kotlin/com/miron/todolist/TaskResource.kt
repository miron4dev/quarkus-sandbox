package com.miron.todolist

import com.miron.todolist.extension.logger
import com.miron.todolist.model.CreateTaskRequest
import com.miron.todolist.model.DeleteTaskRequest
import com.miron.todolist.model.GetAllTasksRequest
import com.miron.todolist.model.GetTaskRequest
import com.miron.todolist.model.Task
import com.miron.todolist.service.TaskService
import java.util.UUID
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces

@Path("tasks")
@Produces("application/json")
@Consumes("application/json")
class TaskResource @Inject constructor(
        private val taskService: TaskService
) {

    @GET
    @Path("/{id}")
    fun get(@PathParam("id") id: UUID, request: GetTaskRequest): Task? {
        log.info("Fetching task by id {}", id)
        return taskService.get(request.userId, id)
    }

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: UUID, request: DeleteTaskRequest) {
        log.info("Deleting task with id {}", id)
        taskService.delete(request.userId, id)
    }

    @PUT
    fun create(task: CreateTaskRequest): UUID {
        log.info("Creating the new task {}", task)
        val uuid = taskService.create(task)
        log.info("Created {}", uuid)
        return uuid
    }

    @GET
    fun getAll(request: GetAllTasksRequest): List<Task> {
        return taskService.getAll(request.userId)
    }

    companion object {
        private val log = logger<TaskResource>()
    }
}