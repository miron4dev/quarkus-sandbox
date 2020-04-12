package com.miron.todolist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.miron.todolist.extension.logger
import com.miron.todolist.model.GetTaskRequest
import com.miron.todolist.model.Task
import com.miron.todolist.service.TaskService
import javax.inject.Inject
import javax.inject.Named

@Named("get-task-by-id")
class GetTaskHandler @Inject constructor(private val taskService: TaskService) : RequestHandler<GetTaskRequest, Task?> {

    override fun handleRequest(input: GetTaskRequest, context: Context): Task? {
        val uuid = input.id
        log.info("Fetching task by id {}", uuid)

        return taskService.get(uuid)
    }

    companion object {
        private val log = logger<GetTaskHandler>()
    }

}